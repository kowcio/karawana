package karawana.web.controller;

import karawana.entities.Group;
import karawana.entities.User;
import karawana.service.GroupService;
import karawana.service.LocationService;
import karawana.service.UserService;
import karawana.utils.TestObjectFabric;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    @Autowired
    Environment environment;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage(HttpSession session) {
        checkCache();
        ModelAndView mav = new ModelAndView("/pages/main");
//        User generatedUserIDKeptInSession = userService.getRandomUser();

        String sessionID = session.getId();
        Group group;
        User user = null;

        if (session.isNew()) {


            String sessionId = sessionID;
            String userName = "User" + sessionId.substring(0, 4);
            group = TestObjectFabric.getGroupEmpty();
            user = TestObjectFabric.getUser(userName);
            group.builder().user(user).build();
            group = groupService.saveGroup(group);

            session.setAttribute(sessionID, group.getGroupName());
            session.setAttribute(sessionID, group.getUsers());

            log.info("Created new group for new user = {}", group.toString());

        } else {
            String groupName = session.getAttribute(sessionID).toString();
            Optional<Group> groupOptional = groupService.getGroupByName(groupName);
            if (groupOptional.isPresent()) {
                group = groupOptional.get();
            } else {
                throw new RuntimeException("We did not found the group by the groupname for given session ID, It should always be  in the database. Created when we first use the service. ");
            }
        }

//        session.setAttribute(SESSION_VAR.latestLocations(groupId), new HashMap<Long, Location>(0));
        long sessionTimeLeft = System.currentTimeMillis() - session.getLastAccessedTime();
        //if session  20 min
        mav.addObject("group", group);
        mav.addObject("user", user);
        mav.addObject(SESSION_VAR.SESSION_ID, sessionID);
        mav.addObject("countdown", sessionTimeLeft);

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
