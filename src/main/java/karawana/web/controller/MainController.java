package karawana.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController extends AbstractController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    @Autowired
    CacheManager         cacheManager;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        checkCache();
        return "/pages/main";
    }

    private void checkCache() {
        log.info("Checking cache = {}", cacheManager.getCacheNames());
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {

        return "/pages/login";
    }

    @RequestMapping(value = "/success", method = RequestMethod.GET)
    public String successAfterLogin() {
        log.info("Login successful.");
        return redirect("/");
    }

    @ResponseBody
    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public String login2() {
        return "404";
    }
}
