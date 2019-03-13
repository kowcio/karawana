package karawana.repository;

import karawana.Application;
import karawana.utils.TestObjectFabric;
import karawana.entities.Group;
import karawana.entities.User;
import karawana.repositories.GroupRepository;
import karawana.repositories.UserRepository;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
@Transactional
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Ignore
public class UserRepositoryTest {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private UserRepository userRepository;
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Test
    public void addGroupThenAddUser() {

        Group group = TestObjectFabric.getGroupWithOneUser();

        group = groupRepository.save(group);
        log.info(group.toString());

        Group groupAfterSave = groupRepository.findByGroupName(group.getGroupName());
        log.info(groupAfterSave.getGroupName());

        assertEquals("Group name mismatch.", group, groupAfterSave);

    }

    //    @Ignore
    @Test
    public void testSaveSingleUser() {

        User user = TestObjectFabric.getUser();
        user = userRepository.save(user);
        log.info(user.toString());
        assertTrue("Usee has no ID from hibernate ", user.getId()!= null) ;


    }
}