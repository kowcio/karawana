package allinone.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String mainPage() {
        
        return "/pages/main";
    }
    
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        
        return "/pages/login";
    }
    
    @ResponseBody
    @RequestMapping(value = "/login2", method = RequestMethod.GET)
    public String login2() {
        System.out.println("asd");
        return "404";
    }
}
