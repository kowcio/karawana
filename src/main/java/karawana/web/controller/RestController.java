package karawana.web.controller;

import karawana.entities.Group;
import karawana.entities.Location;
import karawana.repositories.UserRepository;
import karawana.service.GroupService;
import karawana.service.LocationService;
import karawana.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@ResponseBody
@RequestMapping(value = "/api", method = RequestMethod.GET)

public class RestController {

    private final Logger log = LoggerFactory.getLogger(getClass());


    @Autowired
    GroupService groupService;
    @Autowired
    LocationService locationService;
    @Autowired
    UserService userService;


    @RequestMapping(value = "/newgroup/", method = RequestMethod.POST)
    public ModelAndView addGroup() {
        ModelAndView mav = new ModelAndView("/pages/main");
        mav.addObject("User", userService.getRandomUser());
        return mav;
    }

    @RequestMapping(value = "/updateMyLocation", method = RequestMethod.POST)
    @ResponseBody
    public String updateMyLocation(
            HttpServletRequest httpServletRequest,
            HttpSession session,
            @RequestBody Location location
    ) {
        System.out.println(httpServletRequest.getSession().getId());
        System.out.println(location.toString());
        Long userId = (Long) session.getAttribute("userId");
        location.setUserId(userId);
        location.setCreatedDate(LocalDateTime.now());
        log.info("Saved location = {}", location.toString());
        Optional<Location> saveLocation = locationService.saveUserLocation(location);
        if (saveLocation.isPresent())
            return saveLocation.get().toString();
        else
            return "Saved location";
    }


}
