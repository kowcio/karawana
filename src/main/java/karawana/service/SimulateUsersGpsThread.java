package karawana.service;

import karawana.entities.Group;
import karawana.repositories.GroupRepository;
import karawana.utils.TestObjectFabric;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.text.SimpleDateFormat;
import java.util.Optional;


@Service
@Transactional
public class SimulateUsersGpsThread {

    private static final Logger log = LoggerFactory.getLogger(SimulateUsersGpsThread.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    @Inject
    GroupService groupService;

    @Inject
    GroupRepository groupRepository;

    @Scheduled(fixedRate = 15000)
    public void reportCurrentTime() {


//        log.info("Generating users for test group to check and provide data for front end.");

for(int i = 1 ; i <3 ; i ++){
        Long groupId = Long.valueOf(i);
        Optional<Group> groupById = groupRepository.getOneById(groupId);
        Group testedGroup;
        if (groupById.isPresent())
            testedGroup = groupById.get();
        else
            testedGroup = TestObjectFabric.getGroupEmpty();
        if (testedGroup.getUsers().size() <= 4)
            testedGroup.addUser(TestObjectFabric.getUser("userBot"));
        testedGroup.getUsers()
                .stream()
                .forEach(u -> u.addLocation(TestObjectFabric.getLocation()));

        groupService.saveGroup(testedGroup);
        log.info("Added location for all the users on group : {}", groupId);
//        testedGroup = groupService.getGroupById(2L).get();
//        testedGroup.getUsers()
//                .stream()
//                .forEach(u -> u.addLocation(TestObjectFabric.getLocation()));
//        groupService.saveGroup(testedGroup);

    }}


}
