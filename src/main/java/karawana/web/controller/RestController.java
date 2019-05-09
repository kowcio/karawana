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
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.ReplayProcessor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;

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
        location.setCreatedDate(LocalDateTime.now());
        log.info("session : {}", session.getId());
        //set group
        String userName = session.getAttribute("userName");
        //user should be present at this point - all created at first request
        User userUpdate = userService.getUserByName(userName);
        location.setUser_id(userUpdate.getId());
        locationService.saveUserLocation(location);
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
                groupMono.delayElements(Duration.ofSeconds(2)), 1);
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
            log.info("Saved group with new user. Debug ! = {} ", existingGroup.toString());
            return existingGroup;
        }

        return TestObjectFabric.getGroupEmpty();

    }


    private ReplayProcessor<ServerSentEvent<Group>> replayProcessor =
            ReplayProcessor.<ServerSentEvent<Group>>create(100);

    @ResponseBody
    @GetMapping(path = "/gr/{groupName}", produces = "text/event-stream")
    public Flux<ServerSentEvent<Group>> subscribeChatMessages_spring5(
            @PathVariable("groupName") String groupName,
            WebSession webSession
//            @RequestHeader(name = "last-event-id", required = false) String lastEventId
    ) {
        log.info("Subscribing for data.... {}", webSession.getId());
        Optional<Group> groupByName = groupService.getGroupByName(groupName);
        Group msg;
        if (groupByName.isPresent())
            msg = groupByName.get();
        else msg = TestObjectFabric.getGroupWithOneUser("grNameTest");
        ServerSentEvent<Group> event = ServerSentEvent.builder(msg)
                .event("sending group update")
                .id(String.valueOf(msg.getId())).build();
        replayProcessor.onNext(event);

//        Integer lastId = (lastEventId != null) ? Integer.parseInt(lastEventId) : null;
        return replayProcessor;//.filter(x -> lastId == null || x.data().getId()> lastId);
    }

    @ResponseBody
    @GetMapping(path = "/gr1/{groupName}", produces = "text/event-stream")
    public Flux<Group> test2(
            @PathVariable("groupName") String groupName,
            WebSession webSession
//            @RequestHeader(name = "last-event-id", required = false) String lastEventId
    ) {
        log.info("Subscribing for data.... {}", webSession.getId());
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Group> events =
                Flux.fromStream(Stream.generate(
                        () -> TestObjectFabric.getGroupWithOneUser(LocalDateTime.now().toString())));
        return Flux.zip(events, interval, (Group key, Long value) -> key);
    }


}
