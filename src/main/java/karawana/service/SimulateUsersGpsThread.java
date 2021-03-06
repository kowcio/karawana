package karawana.service;

import karawana.entities.Group;
import karawana.entities.Location;
import karawana.repositories.GroupRepository;
import karawana.utils.TestObjectFabric;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Optional;


@Service
@Singleton
@Transactional
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class SimulateUsersGpsThread {

    private static final Logger log = LoggerFactory.getLogger(SimulateUsersGpsThread.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Inject
    GroupService groupService;

    @Inject
    GroupRepository groupRepository;

    @Inject
    LocationService locationService;

    public static boolean firstTimeout = true;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    @Scheduled(fixedRate = 6000)
    public void reportCurrentTime() throws InterruptedException {
//        log.info("Generating users for test group to check and provide data for front end.");

        if (firstTimeout) {
            Thread.sleep(20000);
            firstTimeout = false;
        }

        for (int i = 1; i < 2; i++) {
            Long groupId = Long.valueOf(i);
            Optional<Group> groupById = groupRepository.getOneById(groupId);
            Group testedGroup;
            if (groupById.isPresent())
                testedGroup = groupById.get();
            else
                testedGroup = TestObjectFabric.getGroupEmpty();


            if (testedGroup.getUsers().size() < 2) {
                testedGroup.addUser(TestObjectFabric.getUser());
                groupRepository.saveAndFlush(testedGroup);
            }
            entityManager.detach(testedGroup);

            testedGroup.getUsers()
                    .forEach(u ->
                    {
                        final Location location = TestObjectFabric.getLocation(u.getId());
                        final Location save = locationService.save(location);
                    });


            log.info("Added location for all the users on group :{}",testedGroup);
        }
//        testedGroup = groupService.getGroupById(2L).get();
//        testedGroup.getUsers()
//                .stream()
//                .forEach(u -> u.addLocation(TestObjectFabric.getLocation()));
//        groupService.saveGroup(testedGroup);

    }


}
