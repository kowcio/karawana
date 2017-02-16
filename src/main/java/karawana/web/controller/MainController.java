package karawana.web.controller;

import karawana.entities.Group;
import karawana.entities.Location;
import karawana.entities.User;
import karawana.service.GroupService;
import karawana.service.LocationService;
import karawana.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.*;

@Controller
public class MainController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    CacheManager cacheManager;
    @Autowired
    GroupService groupService;
    @Autowired
    LocationService locationService;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage(HttpSession session) {
        checkCache();
        ModelAndView mav = new ModelAndView("/pages/main");
        User generatedUserIDKeptInSession = userService.getRandomUser();
        String sessionId = session.getId();
        //check user if it is already in DB
        //make ID as string ? UUID ?
        String groupName = "group" + sessionId.substring(0, 4);
//        String groupName = "groupMocked1";

        String userName = "User" + sessionId.substring(0, 4) ;
        User user = User.builder()
                .name(userName)
                .color(new SecureRandom().nextInt(800000) + 100000)
                .createdDate(LocalDateTime.now())
                .build();
        List<User> users = new ArrayList<>();
        users.add(user);
//TODO session restore and etc
        Group group = Group.builder()
                .groupName(groupName)
                .createdDate(LocalDateTime.now())
                .users(users)
                .build();


        Long groupId = (Long) session.getAttribute(SESSION_VAR.GROUP_ID);
        Long userId = (Long) session.getAttribute(SESSION_VAR.USER_ID);


        if (groupId == null || userId == null) {
            group = groupService.saveGroup(group);
            groupId = group.getId();
            userId = group.getUsers().iterator().next().getId();
            session.setAttribute(SESSION_VAR.GROUP_ID, groupId);
            session.setAttribute(SESSION_VAR.USER_ID, userId);
            log.info("Created new group for new user = {}", group.toString());
        } else {
            group = groupService.getGroupById(groupId).get();
        }


//        session.setAttribute(SESSION_VAR.latestLocations(groupId), new HashMap<Long, Location>(0));
        long sessionTimeLeft = System.currentTimeMillis() - session.getLastAccessedTime();
        //if session  20 min
        mav.addObject("group", group);
        mav.addObject("user", user);
        mav.addObject(SESSION_VAR.SESSION_ID, sessionId);
        mav.addObject("countdown", sessionTimeLeft);

        //redirect na grupe ?
        return mav;
    }

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
