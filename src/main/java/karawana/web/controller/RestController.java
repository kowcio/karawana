package karawana.web.controller;

import karawana.config.DestinationsConfig;
import karawana.config.MessageListenerContainerFactory;
import karawana.entities.Group;
import karawana.entities.Location;
import karawana.entities.User;
import karawana.repositories.LocationRepository;
import karawana.repositories.ReactiveGroupRepository;
import karawana.service.GroupService;
import karawana.service.LocationService;
import karawana.service.UserService;
import karawana.utils.TestObjectFabric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import javax.inject.Inject;
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

    @Inject
    GroupService groupService;
    @Inject
    GroupService groupRepository;
    @Inject
    LocationService locationService;
    @Inject
    ReactiveGroupRepository reactiveGroupRepository;
    @Inject
    UserService userService;

    @Inject
    LocationRepository locationRepository;

    @Inject
    private AmqpTemplate amqpTemplate;

    @Inject
    private AmqpAdmin amqpAdmin;

    @Inject
    private DestinationsConfig destinationsConfig;

    @Inject
    private MessageListenerContainerFactory messageListenerContainerFactory;

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
        location.setLat(location.getLat() + new SecureRandom().nextDouble() / 25);

        // test add lat for display

        location.setCreatedDate(LocalDateTime.now());
        log.info("session : {}", session.getId());
        //set group
        String userName = session.getAttribute("userName");
        //user should be present at this point - all created at first request
        User userUpdate = userService.getUserByName(userName);
        Long userId = userUpdate.getId();
        if (userId.equals(null)) throw new RuntimeException("UserId nie ma prawa być nullem kurwa");
        location.setUserId(userId);
        Location savedLocation = locationService.save(location);
        Long groupId = session.getAttribute("groupId");


//        log.info("Send location {} to queue {}", location, userName);
//        //SEND MESSAGE TO QUEUE - create stuff in main controller
//        Properties queueProperties = amqpAdmin.getQueueProperties(userName);
//        log.info("Properties for queue userName:{}", queueProperties);
//        //send data
//        //final DestinationsConfig.DestinationInfo d = destinationsConfig.getQueues().get(userName);
//        amqpTemplate.convertAndSend(group.getGroupName(), group.getGroupName(), location.toString());
//        //grab data with other locations (instead of repo sql query)
//        String msg = (String) amqpTemplate.receiveAndConvert(userName);
//        log.info("Received msg from rabbit = {}", msg);
//        //end rabbitmq declarations


//        Optional<Group> group = groupService.getGroupById(groupId);
//        Group group1 = group.get();
//        mav.addAttribute("users", group1.getUsers());
//        mav.addAttribute("group", group1);
//        ReactiveDataDriverContextVariable reactiveDataDriverContextVariable = new ReactiveDataDriverContextVariable(Flux.fromIterable(
//                group.get().getUsers()).delayElements(Duration.ofSeconds(2)),
//                1);
//        mav.addAttribute("users",                reactiveDataDriverContextVariable);
//this is blocked by JDBC anyway, move this wrap to the repository level ?
        log.info("Updated location for user : {}, Location : {}", userUpdate.getName(), savedLocation.printCoords());
        Group groupWith10Locations = groupService.getGrouptWith10LatestLocations(groupId);
        Mono<Group> just = Mono.just(groupWith10Locations);
        groupWith10Locations
                .getUsers()
                .stream()
                .peek(u -> log.info("g:{}, u:{}, l:{}, fid:{}",
                        u.getGroupId(), u.getName(), u.getLocations().size(),
                        u.getLocations().get(0).getId()))
                .count()
        ;


        //get last ocation and add
        return just;
    }

//    @ResponseBody
//    @RequestMapping("/react/groups")
//    public List<Group> reactiveUpdateLocatio(
////            @RequestBody Location location,
//            WebSession session,
//            Model mav
//    ) {
//        log.info("session : {}", session.getId());
//        List<Group> groupMono = groupService.getTop10();
////        ReactiveDataDriverContextVariable attributeValue = new ReactiveDataDriverContextVariable(
////                groupMono
////                        .delayElements(Duration.ofSeconds(2)), 1);
////        mav.addAttribute("groups",
////                attributeValue);
//        return groupMono;
//    }

    @ResponseBody
    @RequestMapping("/group3/{groupId}")
    public Group groupByID(
            WebSession session,
            @PathVariable String groupId
    ) {

        log.info("session group3 : {}", session.getId());
        Long groupId1 = Long.valueOf(groupId);
        Group groupWith10Locations = groupService.
                getGrouptWith10LatestLocations(groupId1);
        log.info(groupWith10Locations.toString());
        return groupWith10Locations;
    }


    @RequestMapping(value = "/changeGroup/{groupName}", method = RequestMethod.POST)
    @ResponseBody
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
            Group groupEmpty = TestObjectFabric.getGroupEmpty();
            groupEmpty.addUser(newUser);
            Group group = groupService.saveGroup(groupEmpty);
            session.getAttributes().put(SESSION_VAR.USER_ID, userID);
            session.getAttributes().put(SESSION_VAR.GROUP_ID, group.getId());
            log.info("Saved group with new user. {} ", group.toString());
            log.info(grouID);
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


//    @Inject
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
