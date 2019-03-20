package karawana.web.controller;

import karawana.entities.Group;
import karawana.entities.User;
import karawana.service.GroupService;
import karawana.service.LocationService;
import karawana.service.UserService;
import karawana.utils.TestObjectFabric;
import org.apache.catalina.session.StandardSession;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.env.Environment;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.annotation.SessionScope;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
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
@SessionScope
@PreAuthorize("hasAuthority('ROLE_ANONYMOUS')")
//@Scope("session")
//@Scope(value= WebApplicationContext.SCOPE_SESSION, proxyMode= ScopedProxyMode.TARGET_CLASS)
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

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage(HttpSession httpSession,
                                 HttpServletRequest request,
                                 HttpServletResponse response
//                                 Model model
    ) {
         httpSession = request.getSession();
//
        HttpSession session = request.getSession();
        if (request.getParameter("JSESSIONID") != null) {
            Cookie userCookie = new Cookie("JSESSIONID", request.getParameter("JSESSIONID"));
            response.addCookie(userCookie);
        } else {
            String sessionId = session.getId();
            Cookie userCookie = new Cookie("JSESSIONID", sessionId);
            response.addCookie(userCookie);
        }
//

        ModelAndView mav = new ModelAndView("/pages/main");
        checkCache();
//        User generatedUserIDKeptInSession = userService.getRandomUser();

        String sessionID = httpSession.getId();
        Group group = null;
        User user = null;
        log.info("Is httpSession new ? : {}", httpSession.isNew());
        if (httpSession.isNew()) {
            log.info("sessionId:{}", sessionID);

            String userName = "UserTestName";
            user = TestObjectFabric.getUser(userName);
            group = TestObjectFabric.getGroupEmpty();
            group.getUsers().add(user);

            group = groupService.saveGroup(group);
            user.setGroup_id(group.getId());
            Iterator<User> iterator = group.getUsers().iterator();
            user = iterator.next();

            httpSession.setAttribute(GROUP_ID, group.getId());
            httpSession.setAttribute(USER_NAME, user.getId());
            log.info("Created new group for new user = {}", group.toString());
            httpSession.setAttribute("sessionId", sessionID);

        } else {
            log.info("Session is not new !!  ");

            String groupName = httpSession.getAttribute(GROUP_ID).toString();
            Optional<Group> groupOptional = groupService.getGroupById(Long.valueOf(groupName));
            if (groupOptional.isPresent()) {
                group = groupOptional.get();
            } else {
                throw new RuntimeException("We did not found the group by the groupname for given httpSession ID, It should always be  in the database. Created when we first use the service. ");
            }
            Long userId = (Long) httpSession.getAttribute(USER_NAME);
            log.info("sessionId:{}", sessionID);
            user = userService.getUserById(userId);
            log.info("Group for established httpSession \n {}", group.toString());
        }
//        httpSession.setAttribute(SESSION_VAR.latestLocations(groupId), new HashMap<Long, Location>(0));
        long sessionTimeLeft = System.currentTimeMillis() - httpSession.getLastAccessedTime();
        //if httpSession  20 min
        mav.addObject("group", group);
        mav.addObject("user", user);
        mav.addObject(SESSION_VAR.SESSION_ID, sessionID);
        mav.addObject("countdown", sessionTimeLeft);
        mav.addObject("httpSession", httpSession);

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
