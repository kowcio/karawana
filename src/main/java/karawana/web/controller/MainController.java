package karawana.web.controller;

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
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.WebSession;
import org.thymeleaf.spring5.context.webflux.IReactiveDataDriverContextVariable;
import org.thymeleaf.spring5.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

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

    @Inject
    ReactiveSessionRepository reactiveSessionRepository;


    @RequestMapping(value = "/"
//            ,method = RequestMethod.GET
//            ,produces = MediaType.TEXT_EVENT_STREAM_VALUE
    )
    public String mainPage(
            @RequestParam(defaultValue = "") String userName,
            @RequestParam(defaultValue = "") String groupName,
                WebSession session,
            final Model mav
    ) {
        // data streaming, data driven mode.
        log.info("Session : {}", session.getId());
        //saving a thing in session saves the session for the redis store
        //other wise we are getting new session always and always
        //GOLDEN LINE DO NOT DELETE
        session.getAttributes().putIfAbsent("userName", userName);
        log.info("Session : {}", session);
        IReactiveDataDriverContextVariable reactiveDataDrivenMode =
                new ReactiveDataDriverContextVariable(
                        userService.findAll(), 1);
        mav.addAttribute("users", reactiveDataDrivenMode);
        mav.addAttribute("session_static", session.getId());
//        mav.addAttribute("session_test", note);
        return "layout";
    }

    @GetMapping(path = "/users",
            produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> feed(WebSession session
    ) {
        log.info("Session : {}", session.getId());
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
