package karawana.web.controller;

import karawana.entities.BeanUser;
import karawana.entities.User;
import karawana.service.GroupService;
import karawana.service.LocationService;
import karawana.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

@Controller
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

//    @Inject
//    BeanUser beanUser;


    @RequestMapping(
            value = "/"
//            ,method = RequestMethod.GET
//            ,produces = MediaType.TEXT_EVENT_STREAM_VALUE
 )
    public String mainPage(
//            HttpSession httpSession,
                          final Model mav
    ) {
//        String user = beanUser.getUser().toString();
//        log.info("SessionId:{}", httpSession.getId());
//        log.info("User:{}", beanUser.getUser());
        //if httpSession  20 min
//        mav.addAttribute("user", beanUser.getUser());
//        mav.addAttribute(SESSION_VAR.SESSION_ID, httpSession.getId());
//        mav.addAttribute(SESSION_VAR.SESSION_ID, "qweasdzxc");
//        mav.addAttribute("user", "qweasdzxc");

        // data streaming, data driven mode.

        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(
                        userService.findAll(), 1);

        mav.addAttribute("users", reactiveDataDrivenMode);

        return "layout";
    }


    @GetMapping(path = "/users",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> feed() {
        return this.userService.findAll();

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
