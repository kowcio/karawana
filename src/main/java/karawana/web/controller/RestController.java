package karawana.web.controller;

import karawana.entities.Group;
import karawana.entities.Location;
import karawana.entities.User;
import karawana.repositories.ReactiveGroupRepository;
import karawana.service.GroupService;
import karawana.service.LocationService;
import karawana.service.UserService;
import karawana.utils.TestObjectFabric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/api", method = RequestMethod.GET)
public class RestController {

    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    GroupService groupService;
    @Autowired
    GroupService groupRepository;
    @Autowired
    LocationService locationService;
    @Autowired
    ReactiveGroupRepository reactiveGroupRepository;
    @Autowired
    UserService userService;

    @ResponseBody
    @RequestMapping(value = "/updateMyLocation"
            , method = {
//            RequestMethod.GET,
            RequestMethod.POST
    }
//            , consumes = APPLICATION_JSON_VALUE
//            , produces = APPLICATION_JSON_VALUE
//            produces =Arrays.arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE)
    )
//    , produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<Group> mainPageWithLocation(
            @RequestBody Location location,
            Model mav,
            WebSession session
    ) {

        //test add lat = display

        location.setLat(location.getLat() + new SecureRandom().nextDouble() / 100);

        // test add lat for display

        location.setCreatedDate(LocalDateTime.now());
        log.info("session : {}", session.getId());
        //set group
        String userName = session.getAttribute("userName");
        //user should be present at this point - all created at first request
        User userUpdate = userService.getUserByName(userName);
        location.setUser_id(userUpdate.getId());
        Optional<Location> savedLocation = locationService.saveUserLocation(location);
        Long groupId = session.getAttribute("groupId");
        Optional<Group> group = groupService.getGroupById(groupId);
        Group group1 = group.get();
//        mav.addAttribute("users", group1.getUsers());
//        mav.addAttribute("group", group1);
//        ReactiveDataDriverContextVariable reactiveDataDriverContextVariable = new ReactiveDataDriverContextVariable(Flux.fromIterable(
//                group.get().getUsers()).delayElements(Duration.ofSeconds(2)),
//                1);
//        mav.addAttribute("users",                reactiveDataDriverContextVariable);
//this is blocked by JDBC anyway, move this wrap to the repository level ?
        log.info("Updated location for user : {}, Location : {}", userUpdate.getName(), savedLocation.get().printCoords());
        Mono<Group> just = Mono.just(groupService.getGroupById(groupId).get());
        return just;
    }

    @ResponseBody
    @RequestMapping("/react/groups")
    public Flux<Group> reactiveUpdateLocatio(
//            @RequestBody Location location,
            WebSession session,
            Model mav
    ) {
        log.info("session : {}", session.getId());
        Flux<Group> groupMono = groupService.getTopGroupsReactive();
        ReactiveDataDriverContextVariable attributeValue = new ReactiveDataDriverContextVariable(

                groupService.
                        getTopGroupsReactive()
                        .delayElements(Duration.ofSeconds(2)), 1);
        mav.addAttribute("groups",
                attributeValue);
        return groupMono;
    }

    @RequestMapping(value = "/changeGroup/{groupName}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Group changeGroup(
//            HttpServletRequest httpServletRequest,
//            HttpSession session,
            WebSession session,
            @PathVariable String groupName
    ) {

        Long userID = session.getAttribute(SESSION_VAR.USER_ID);

        log.info("Session : {}, user : '{}'", session.getId(), userID);
        if (userID == null || userID == 0L) {
            User newUser = TestObjectFabric.getUser("newUserGroupOnChangeGroupRequest");
//            userId = userService.saveUser(newUser).getId();
            Group groupEmpty = TestObjectFabric.getGroupEmpty();
            groupEmpty.addUser(newUser);
            Group group = groupService.saveGroup(groupEmpty);
            group = groupService.getGroupById(group.getId()).get();
            session.getAttributes().put(SESSION_VAR.USER_ID, userID);
            session.getAttributes().put(SESSION_VAR.GROUP_ID, group.getId());
            log.info("Saved group with new user. {} ", group.toString());
            log.info(groupName);
            return group;
        }

        Optional<Group> optNewGroup = groupService.getGroupByName(groupName);

        if (optNewGroup.isPresent()) {
            Group existingGroup = optNewGroup.get();
            existingGroup.getUsers().add(userService.getUserById(Long.valueOf(userID)));
            existingGroup = groupService.saveGroup(existingGroup);
            session.getAttributes().put(SESSION_VAR.GROUP_ID, existingGroup.getId());
            log.info("Saved group with new user.{} ", existingGroup.toString());
            return existingGroup;
        }

        return TestObjectFabric.getGroupEmpty();
    }

    @ResponseBody
    @GetMapping("/react/{groupName}")
    public Flux<ServerSentEvent<Group>> streamEvents(
            WebSession webSession,
            @PathVariable String groupName
    ) {

        Optional<Group> groupByName = groupService.getGroupByName(groupName);
        if (groupByName.isPresent())
            return Flux.interval(Duration.ofSeconds(1))
                    .map(sequence -> ServerSentEvent.<Group>builder()
                            .id(String.valueOf(sequence))
                            .event("Data for group " + groupName)
                            .data(groupByName.get())
                            .build());

        return Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<Group>builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data(TestObjectFabric.getGroupWithOneUser("TestGroupNotInDb"))
                        .build());
    }


//    @Autowired
//    private KafkaTemplate<String, Object> kafkaTemplate = new KafkaTemplate<String, String>();
//
//    @KafkaListener(topics = "topic1")
//    public void receiveTopic1(ConsumerRecord<?, ?> consumerRecord) {
//        System.out.println("Receiver on topic1: "+consumerRecord.toString());
//    }
//
//    public void send(String topic, String payload) {
//        kafkaTemplate.send(topic, payload);
//        System.out.println("Message: "+payload+" sent to topic: "+topic);
//    }
//

    /**
     * TODO - works and quite after elving the browser. Basic for updating the users.
     * Problem - a lot of threads spawning for constant updates.
     *
     * @return
     */
    @ResponseBody
    @GetMapping(path = "/stream-flux", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<List<Group>> streamFlux() {

        List<Group> groups = reactiveGroupRepository.findTop15ByOrderByIdDesc();
        log.info("Groups count : {}", groups.size());

        Flux<List<Group>> map = Flux.interval(Duration.ofSeconds(5)
                , Schedulers.newElastic("Infinite stream flux.")
        )
                .map(sequence -> {

//                            Group group = groupService.getGroupById(1L).get();
//                            List<Group> liveGroups = reactiveGroupRepository.findTop15ByOrderByIdDesc() ;
                            Mono<List<Group>> blockingWrapper = Mono.fromCallable(() -> {
                                return reactiveGroupRepository.findTop15ByOrderByIdDesc();
                            });

                            List<Group> liveGroups = blockingWrapper
                                    .subscribeOn(Schedulers.newElastic("Infinite stream flux."))
                                    .single().block(Duration.ofSeconds(2));//block and timeout at 2 seconds for sql to grab the data

//                            reactor.netty.ioWorkerCount
//                            reactor.netty.ioSelectCount
//                            reactor.netty.pool.maxConnections
                            liveGroups.forEach(group -> {
                                log.info("Group:{}, users:{}, locations:{}",
                                        group.getGroupName(),
                                        group.getUsers().size(),
                                        group.getUsers()
                                                .stream()
                                                .mapToInt(u -> u.getLocations().size()).sum()
                                );
                            });
                            return liveGroups;
                        }
                ).subscribeOn(Schedulers.newElastic("new ELastic"));
        return map;
    }

    //    https://docs.spring.io/spring/docs/current/spring-framework-reference/web-reactive.html#webflux-fn
//    public String handle(@SessionAttribute User user) {
    @GetMapping("/stream-sse")
    public Flux<ServerSentEvent<String>> streamEvents() {
        Flux<ServerSentEvent<String>> map = Flux.interval(Duration.ofSeconds(1))
                .map(sequence -> ServerSentEvent.<String>builder()
                        .id(String.valueOf(sequence))
                        .event("periodic-event")
                        .data("SSE - " + LocalTime.now().toString())
                        .build());
        return map;
    }

    @ResponseBody
    @GetMapping(path = "/infinite", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<Group> getStreamOfGroups() {


        return Flux.generate(sink ->
                sink.next(
//            TestObjectFabric.getGroupWithOneUser("TestGroupNotInDb"+LocalDateTime.now())
                        groupService.getGroupById(1L).get()
                ));


    }












}
