package karawana.web.controller;

import karawana.config.DestinationsConfig;
import karawana.config.MessageListenerContainerFactory;
import karawana.entities.Group;
import karawana.entities.Location;
import karawana.entities.User;
import karawana.repositories.GroupRepository;
import karawana.service.GroupService;
import karawana.service.LocationService;
import karawana.service.UserService;
import karawana.utils.TestObjectFabric;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.WebSession;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.Duration;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@Transactional
public class MainController {

    public static final String GROUP_ID = "groupId";
    public static final String USER_NAME = "userName";
    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    CacheManager cacheManager;
    @Autowired
    GroupService groupService;
    @Autowired
    LocationService locationService;
    @Autowired
    UserService userService;
    @Autowired
    Environment environment;

    @Autowired
    GroupRepository groupRepository;

    @Inject
    ReactiveSessionRepository reactiveSessionRepository;
    @Inject
    private AmqpTemplate amqpTemplate;

    @Inject
    private AmqpAdmin amqpAdmin;

    @Inject
    private DestinationsConfig destinationsConfig;

    @Inject
    private MessageListenerContainerFactory messageListenerContainerFactory;

    private static final String DELAY_SERVICE_URL = "http://localhost:8080";
    private final WebClient client = WebClient.create(DELAY_SERVICE_URL);

    @RequestMapping(value = "/", method = RequestMethod.GET)
//    , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String mainPage(
//            @RequestParam(defaultValue = "") String groupName,
//            @RequestParam(defaultValue = "firstRequestNewUserName") String userName,
            Model mav,
            WebSession session
    ) {
        Group group;
        User user;
        boolean sessionIsNew = !session.isStarted();
        if (sessionIsNew) {
            log.info("New session : {}", session.getId());
            group = TestObjectFabric.getGroupWithOneUser("group " + session.getId().substring(0, 6));
            user = TestObjectFabric.getUser("test user " + session.getId().substring(0, 6));


            session.getAttributes().put("userName", user.getName());
            group.addUser(user);
            group = groupRepository.save(group);
            User collect = group.getUsers().stream()
                    .peek(u -> log.info(u.toString()))
                    .filter(u -> !u.getName().equals(user.getName()))
                    .collect(Collectors.toList()).get(0);

            session.getAttributes().put(SESSION_VAR.USER_ID, collect.getId());
            session.getAttributes().put(SESSION_VAR.GROUP_ID, group.getId());
            session.getAttributes().put(SESSION_VAR.USER_NAME, user.getName());
//            log.info("Saved group as follows : {}", group);
        } else {
            log.info("Established session : {}", session.getId());
            Long groupId = session.getAttribute(SESSION_VAR.GROUP_ID);
            group = groupService.getGrouptWith10LatestLocations(groupId);
        }


        String userName = session.getAttribute("userName").toString();
//        session.getAttributes().put("session", userName);
        mav.addAttribute("sessionId", session.getId());
        mav.addAttribute("userName", userName);
        mav.addAttribute("groupName", group.getGroupName());
        Set<User> users = group.getUsers();
        mav.addAttribute("users",
                new ReactiveDataDriverContextVariable(Flux.fromIterable(
                        users
                ).delayElements(Duration.ofSeconds(2)), 1));


//        mav.addAttribute("infinite",
//                new ReactiveDataDriverContextVariable(Flux.fromIterable(
//                        groupService.getGroupById(1L).get().getUsers()
//
//                ).delayElements(Duration.ofSeconds(2)), 1));
        //logging data
        Group groupInfinite = groupService.getGroupById(group.getId()).get();
        log.info("Group:{}, users:{}, locations:{}",
                groupInfinite.getGroupName(),
                groupInfinite.getUsers().size(),
                groupInfinite.getUsers()
                        .stream()
                        .mapToInt(u -> u.getLocations().size()).sum()
        );

        mav.addAttribute("group", group);
        mav.addAttribute("view", "/pages/main");



        //CREATE fanout for group and QUEUE  IN USER - not durable

        Properties queueProperties = amqpAdmin.getQueueProperties(userName);

        if (queueProperties == null) {
            log.info("[I54] Creating group fanout:{}", session.getId());

            Exchange ex = ExchangeBuilder.fanoutExchange(group.getGroupName())
                    .durable(true)
                    .build();
            amqpAdmin.declareExchange(ex);

            Queue q = QueueBuilder.durable(userName).build();
            amqpAdmin.declareQueue(q);

            Binding b = BindingBuilder.bind(q)
                    .to(ex)
                    .with(group.getGroupName())
                    .noargs();
            amqpAdmin.declareBinding(b);

            log.info("[I70] Binding successfully created.");
        } else {
            log.info("Queue {} is present.",userName);
        }









        //CREATE QUEUE END


//        return "layout";
        return "layout";


    }


    @ResponseBody
    @RequestMapping(value = "/m/")
//    , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public String mainPageWithLocation(
            @RequestParam(defaultValue = "") String groupName,
            @RequestParam(defaultValue = "") String userName,
            @RequestParam Location location,
            Model mav
    ) {
        log.info("Location : {}", location);
        return "layout";
    }


    @GetMapping("/websession")
    public Mono<String> getSession(WebSession session) {
        session.getAttributes().putIfAbsent("note", "Howdy Cosmic Spheroid!");
        return Mono.just((String) session.getAttributes().get("note"));
    }


    @ResponseBody
    @RequestMapping(value = "/r", method = RequestMethod.GET)
    public ResponseEntity<User> classicUserUpdate(
            HttpSession httpSession
    ) {
//        User user = userBean.getUser();
//        userService.saveUser(userBean.getUser());
//        log.info("SessionID:{}", user.toString());
        return new ResponseEntity<>(TestObjectFabric.getUser(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/t", method = RequestMethod.GET)
    public ReactiveDataDriverContextVariable classicUserUpdateTest() {
        ReactiveDataDriverContextVariable reactiveDataDriverContextVariable =
                new ReactiveDataDriverContextVariable(userService.findAllRxTest());
        return reactiveDataDriverContextVariable;
    }

//    @RequestMapping(value = "/{id}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
//    public String mainPage(@PathVariable Long id,
//                           final Model model
////                           HttpSession httpSession,
////                           HttpServletRequest request,
////                           HttpServletResponse response
//    ) {
//        // data streaming, data driven mode.
//        Flux<User> allRxTest = userService.findAllRxTest();
//        allRxTest.subscribe();
////        allRxTest.toStream()
////                .forEach(u -> log.info("{}",u.toString()));
//
//        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
//                new ReactiveDataDriverContextVariable(userService.findAllRxTest(), 1);
////        model.addAttribute("user", userBean.getUser());
//        model.addAttribute("users", reactiveDataDrivenMode);
//
//
////        IReactiveDataDriverContextVariable reactiveDataDrivenModeDB =
////                new ReactiveDataDriverContextVariable(userService.findMonoUserByID());
////        model.addAttribute("user2", reactiveDataDrivenModeDB);
//
//        return "pages/main :: #allUsers";
//
//    }


//    @GetMapping("/{id}")
//    private Mono<User> getEmployeeById(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }
//
//    @PostMapping("/update_user")
//    public Mono<User> reactiveUserUpdate(
//    ) {
//        User user = userBean.getUser();
//        user.addLocation(new Location());
//        userService.saveUser(userBean.getUser());
//        log.info("Reactive user update : :{}", user.toString());
//        return new ResponseEntity<User>(user, HttpStatus.OK);
//    }


    private void checkCache() {
        log.info("Checking cache = {}", cacheManager.getCacheNames());
    }

//    @Autowired
//    SimpleBrokerMessageHandler sb;

    @ResponseBody
    @RequestMapping(value = "/ws", method = RequestMethod.GET)
    public String testWS() {
        log.info("CONTROLLER IS ON ! ");
        return "s";
    }


}
