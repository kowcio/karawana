package allinone.web.controller;

import allinone.WebSocketConfig;
import allinone.entities.User;
import allinone.service.UserService;
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

@Controller
public class MainController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    CacheManager cacheManager;
    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView mainPage() {
        checkCache();
        System.out.println("CONTROLLER IS ON ! ");

        ModelAndView mav = new ModelAndView("/pages/main");
        mav.addObject("User", userService.getRandomUser());

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
