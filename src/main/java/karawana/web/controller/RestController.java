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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.*;

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
    public Map<Long, Location> updateMyLocation(
            HttpServletRequest httpServletRequest,
            HttpSession session,
            @RequestBody Location location
    ) {
        System.out.println(httpServletRequest.getSession().getId());
        System.out.println(location.toString());
        Long userId = (Long) session.getAttribute(SESSION_VAR.USER_ID);

        location.setUserId(userId);
        location.setCreatedDate(LocalDateTime.now());
        Optional<Location> saveLocation = locationService.saveUserLocation(location);
        Location savedLocation = saveLocation.get();
        Long groupId = (Long) session.getAttribute(SESSION_VAR.GROUP_ID);

//        String sessionGroupName = "latestLocations" + groupId;
        String sessionGroupName = SESSION_VAR.latestLocations(groupId);

        Map<Long, Location> groupLatestLocations =
                (HashMap<Long, Location>) session.getAttribute(sessionGroupName);
        if (groupLatestLocations == null) return new HashMap<>();

        groupLatestLocations.put(userId, savedLocation);

        log.info("latest locations update = {}", groupLatestLocations.toString());


        return groupLatestLocations;
    }

    @RequestMapping(value = "/getGroupLocation", method = RequestMethod.POST)
    @ResponseBody
    public Group getGroupLocation(
            HttpServletRequest httpServletRequest,
            HttpSession session
    ) {
        Long groupId = (Long) session.getAttribute("groupId");
        Optional<Group> group = groupService.getGroupLocations(groupId);
        if (group.isPresent()) {
            Group gr = group.get();


            return gr;
        } else
            return new Group();
    }

}
