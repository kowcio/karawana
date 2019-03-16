package karawana.service;


import karawana.entities.Group;
import karawana.repositories.GroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class GroupService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired(required = true)
    private GroupRepository groupRepository;

    public Optional<Group> getGroupById(Long groupId) {
        return Optional.of(groupRepository.getOne(groupId));
    }

    public Optional<Group> getGroupByName(String groupName) {
        return Optional.of(groupRepository.findByGroupName(groupName));
    }

    //http://stackoverflow.com/questions/11881479/how-do-i-update-an-entity-using-spring-data-jpa
    public Group saveGroup(Group group) {
//        group
//                .getUsers()
//                .stream()
//                .filter(c->c.getGid()==null)
//                .forEach(c -> c.setGid(group.getId()))

        log.info("Saving group with data : {}", group.toString());
        return groupRepository.save(group);
    }


}
