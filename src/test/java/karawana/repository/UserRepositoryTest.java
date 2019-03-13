package karawana.repository;

import karawana.Application;
import karawana.TestObjectFabric;
import karawana.entities.Group;
import karawana.entities.User;
import karawana.repositories.GroupRepository;
import karawana.repositories.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;
import java.security.SecureRandom;
import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//@Ignore
public class UserRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void addGroupThenAddUser() {

        String groupName = TestObjectFabric.TEST_GROUP_NAME_PREFIX + new SecureRandom().nextInt(99);
        Group group = TestObjectFabric.getGroupWithOneUser();

        group = groupRepository.save(group);

        log.info(group.toString());

        group = groupRepository.findByGroupName(groupName);
        log.info(group.toString());

    }

    @Test
    public void checkIfGroupInDB() {
        Group group = groupRepository.findByGroupName("testGroupName");
        log.info(group.toString());
        assertTrue(group.getId() != null);
    }

    //    @Ignore
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

    //    @Ignore
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