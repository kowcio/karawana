package karawana.web.controller;

import karawana.entities.Group;
import karawana.entities.User;
import karawana.repositories.GroupRepository;
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
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

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
        String sessionId = RequestContextHolder.currentRequestAttributes().getSessionId();
        //check user if it is already in DB
        //make ID as string ? UUID ?
        String groupName = sessionId.substring(0, 4);

        User user = User.builder()
                .id(new Random().nextLong())
                .name("User" + groupName)
                .color(new Random().nextInt(999999))
                .build();
        List<User> users = new ArrayList<>();
        users.add(user);
//TODO session restore and etc
        Group group = Group.builder()
                .id(new Random().nextLong())
                .groupName("group" + groupName)
                .createdDate(LocalDateTime.now())
                .users(users)
                .build();

        //store in session
        session.setAttribute(groupName, group);
        //store in hibernate for testing
        groupService.saveGroup(group);


        long sessionTimeLeft = System.currentTimeMillis() - session.getLastAccessedTime();
        //if session  20 min
        mav.addObject("group", group);
        mav.addObject("sessionId", sessionId);
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
        System.out.println("CONTROLLER IS ON ! ");


        return "s";
    }


}
