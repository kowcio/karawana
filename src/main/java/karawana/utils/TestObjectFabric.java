package karawana.utils;

import karawana.entities.Group;
import karawana.entities.Location;
import karawana.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class TestObjectFabric {


    public static final String TEST_USER_NAME_PREFIX = "testUserName";
    public static final String TEST_GROUP_NAME_PREFIX = "testGroupName";
    private final static Logger log = LoggerFactory.getLogger(TestObjectFabric.class);

    public static User getUser() {
        int color = new SecureRandom().nextInt(800000) + 100000;
        List<Location> locations = new ArrayList<>();
//        locations.add(getLocation(Long.valueOf(color)));
        locations.add(getLocation());
//        locations.add(getLocation(Long.valueOf(color)));

        User user = User.builder()
//                .id(Long.valueOf(color))
                .name(TEST_USER_NAME_PREFIX + color + "")
                .color(color)
                .createdDate(LocalDateTime.now())
                .locations(locations)
                .build();
        return user;
    }

    public static User getUser(String userName) {
        User user = getUser();
        user.setName(userName);
        return user;
    }

    public static Location getLocation() {
        Double longitute = ThreadLocalRandom.current().nextDouble(18,19);
        Double latitude = ThreadLocalRandom.current().nextDouble(54, 55);
        Location location = Location.builder()
                .lat(latitude)
                .lng(longitute)
                .createdDate(LocalDateTime.now())
                .build();
        return location;
    }

    public static Location getLocation(Long userId) {
        Location location = getLocation();
//        location.setUser_id(userId);
        return location;
    }


    public static Group getGroupEmpty() {
        String groupName = TEST_GROUP_NAME_PREFIX + new SecureRandom().nextInt(99);
        Group group = Group.builder()
                .groupName(groupName)
                .createdDate(LocalDateTime.now())
                .build();
        return group;
    }


    public static Group getGroupWithOneUser() {
        Group group = getGroupEmpty();
        group.getUsers().add(getUser());
        return group;
    }

    public static Group getGroupWithOneUser(String groupName) {
        Group group = getGroupWithOneUser();
        group.setGroupName(groupName);
        return group;
    }

}
