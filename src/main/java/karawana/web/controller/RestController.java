package karawana.web.controller;

import karawana.entities.Group;
import karawana.entities.Location;
import karawana.entities.User;
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
import javax.transaction.Transactional;
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
    public Group updateMyLocation(
            HttpServletRequest httpServletRequest,
            HttpSession session,
            @RequestBody Location location
    ) {
        Long userId = (Long) session.getAttribute(SESSION_VAR.USER_ID);
        Long groupId = (Long) session.getAttribute(SESSION_VAR.GROUP_ID);

        if(userId == null)
            log.info("UserID from session is null. WTF !? ");
        if(groupId == null )
            log.info("groupId from session is null. WTF !? ");

        location.setCreatedDate(LocalDateTime.now());
        location.setUser_id(userId);
        locationService.saveUserLocation(location);
//        User user = userService
//                .getUserById(userId)
//                .addLocation(location);
//        Group group = groupService.getGroupById(groupId).get().addUser(user);
//        group = groupService.saveGroup(group);

        Group group = groupService.getGroupById(groupId).get();
        return group;

    }

//    @RequestMapping(value = "/getGroupLocation", method = RequestMethod.POST)
//    @ResponseBody
//    public Group getGroupLocation(
//            HttpServletRequest httpServletRequest,
//            HttpSession session
//    ) {
//        Long groupId = (Long) session.getAttribute(SESSION_VAR.GROUP_ID);
//        Optional<Group> group = groupService.getGroupById(groupId);
//        if (group.isPresent()) {
//            Group gr = group.get();
//            return gr;
//        } else
//            return new Group();
//    }


    @RequestMapping(value = "/changeGroup/{groupName}", method = RequestMethod.POST)
    @ResponseBody
    @Transactional
    public Group changeGroup(
            HttpServletRequest httpServletRequest,
            HttpSession session,
            @PathVariable String groupName
    ) {

        Optional<Group> optNewGroup = groupService.getGroupByName(groupName);
        if (optNewGroup.isPresent()) {
            Group newGroup = optNewGroup.get();
            session.setAttribute(SESSION_VAR.GROUP_ID, newGroup.getId());
            Long userId = (Long) session.getAttribute(SESSION_VAR.USER_ID);
            User user = userService.getUserById(userId);
            Group group = newGroup.addUser(user);
            group = groupService.saveGroup(group);
            log.info("Saved group with new user. Debug ! = {} ", group.toString());
            return group;
        }
        return new Group();

    }
}
