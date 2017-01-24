package allinone.web.controller;

import allinone.WebSocketConfig;
import allinone.entities.Group;
import allinone.entities.User;
import allinone.service.GroupService;
import allinone.service.LocationService;
import allinone.service.UserService;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Scope;
import org.springframework.messaging.simp.broker.SimpleBrokerMessageHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

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
    public ModelAndView mainPage() {
        checkCache();
        ModelAndView mav = new ModelAndView("/pages/main");
        User generatedUserIDKeptInSession = userService.getRandomUser();

        //check user if it is already in DB
        //make ID as string ? UUID ?
        User user = User.builder().id(new Random().nextLong()).build();
        List<User> users = new ArrayList<>();
        users.add(user);

        Group gneratedNewGroup = Group.builder()
                .id(new Random().nextLong())
                .createdDate(DateTime.now())
                .users(users)
                .build();

        mav.addObject("group", gneratedNewGroup);

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
