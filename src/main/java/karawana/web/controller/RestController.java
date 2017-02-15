package karawana.web.controller;

import karawana.entities.Group;
import karawana.entities.Location;
import karawana.entities.User;
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
    public Group updateMyLocation(
            HttpServletRequest httpServletRequest,
            HttpSession session,
            @RequestBody Location location
    ) {
        System.out.println(httpServletRequest.getSession().getId());
        System.out.println(location.toString());
        Long userId = (Long) session.getAttribute(SESSION_VAR.USER_ID);
        location.setCreatedDate(LocalDateTime.now());
        location.setUid(userId);

        Optional<Location> saveLocation = locationService.saveUserLocation(location);
        Location savedLocation = saveLocation.get();

        Long groupId = (Long) session.getAttribute(SESSION_VAR.GROUP_ID);

        Optional<Group> optGroupLatest = groupService.getGroupById(groupId);
        if (optGroupLatest.isPresent()) {
            Group gr = optGroupLatest.get();
            log.info("Size:" + gr.getUsers().size() + " UserId " + " -- " + userId + " groupID:" + groupId + " grUpdated:" + gr.getId());
            return gr;
        } else
            return new Group();

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
    public Group changeGroup(
            HttpServletRequest httpServletRequest,
            HttpSession session,
            @PathVariable String groupName
    ) {


        Optional<Group> optNewGroup = groupService.getGroupByName(groupName);

        if (optNewGroup.isPresent()) {
            Group newGroup = optNewGroup.get();
            Set<User> addUser = newGroup.getUsers();

            session.setAttribute(SESSION_VAR.GROUP_ID, newGroup.getId());

            Long userId = (Long) session.getAttribute(SESSION_VAR.USER_ID);
            User user = userService.getUserById(userId);
            user.setGid(optNewGroup.get().getId());

            user = userService.saveUser(user);
            addUser.add(user);
            newGroup.setUsers(addUser);

            Group group = groupService.saveGroup(newGroup);

            log.info("Saved group with new user. Debug !  ");
            Group test = groupService.getGroupByName(groupName).get();

            return group;
        }
        return new Group();

    }
}
