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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
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
    public ModelAndView mainPage(HttpSession session,
                                 HttpServletRequest req,
                                 HttpServletResponse response
//                                 Model model
    ) {
        ModelAndView mav = new ModelAndView("/pages/main");
        checkCache();
//        User generatedUserIDKeptInSession = userService.getRandomUser();

        String sessionID = session.getId();
        Group group = null;
        User user = null;

        if (session.isNew()) {

            String userName = "UserTestName";
            user = TestObjectFabric.getUser(userName);
            group = TestObjectFabric.getGroupEmpty();
            group.getUsers().add(user);

            group = groupService.saveGroup(group);
            user.setGroup_id(group.getId());
            Iterator<User> iterator = group.getUsers().iterator();
            user = iterator.next();

            session.setAttribute("groupId", group.getId());
            session.setAttribute("userName", user.getName());
            log.info("Created new group for new user = {}", group.toString());
            session.setAttribute("sessionId", sessionID);

        } else {
            String groupName = session.getAttribute("groupId").toString();
            Optional<Group> groupOptional = groupService.getGroupById(Long.valueOf(groupName));
            if (groupOptional.isPresent()) {
                group = groupOptional.get();
            } else {
                throw new RuntimeException("We did not found the group by the groupname for given session ID, It should always be  in the database. Created when we first use the service. ");
            }
            user = userService.getUserByName((String) session.getAttribute("userName"));
            log.info("Group for established session \n {}", group.toString());
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
