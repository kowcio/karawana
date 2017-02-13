package karawana.repository;

import karawana.Application;
import karawana.entities.Group;
import karawana.entities.User;
import karawana.repositories.GroupRepository;
import karawana.repositories.UserRepository;
import karawana.service.GroupService;
import karawana.service.LocationService;
import karawana.service.UserService;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
//@SpringApplicationConfiguration
@Transactional
class UserRepositoryTest {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());


    @Test
    public void addGroupThenAddUser() {

        String groupName = "testGroupName";
        Group group = Group.builder()
                .groupName(groupName)
                .createdDate(LocalDateTime.now())
//                .users(users)
                .build();

        group = groupRepository.save(group);
        log.info(group.toString());
        assertTrue(group.getId() != null);

        User user = User.builder()
                .name("testUserName")
                .color(new SecureRandom().nextInt(800000) + 100000)
                .createdDate(LocalDateTime.now())
                .build();

        //save user to get ID and attach to hibernate context
        user = userRepository.save(user);
        //set user we have to group
        List<User> users = new ArrayList<>();
        users.add(user);
        group.setUsers(users);
        //save all...
        group = groupRepository.save(group);

        group = groupRepository.findByGroupName(groupName);
        log.info(group.toString());
        assertTrue(group.getUsers().size() == 1);

    }

    @Test
    public void checkIfGroupInDB() {
        Group group = groupRepository.findByGroupName("testGroupName");
        log.info(group.toString());
        assertTrue(group.getId() != null);
    }

    @Ignore
    @Test
    public void addGroupWithNullUserList() {

        Group group = Group.builder()
                .groupName("testGroupName")
                .createdDate(LocalDateTime.now())
//                .users(users)
                .build();

        group = groupRepository.save(group);
        log.info(group.toString());
        assertTrue(group.getId() != null);
    }

    @Ignore
    @Test
    public void testSaveSingleUser() {

        User user = User.builder()
                .name("testUserName")
                .color(new SecureRandom().nextInt(800000) + 100000)
                .createdDate(LocalDateTime.now())
                .build();
        user = userRepository.save(user);

        log.info(user.toString());
        assertTrue(user.getId() != null);


    }
}