package karawana.service;


import karawana.entities.Group;
import karawana.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;


@Service
@Transactional
public class GroupService {

    @Autowired(required = true)
    private GroupRepository groupRepository;

    public Optional<Group> getGroupById(Long groupId) {
        return Optional.of(groupRepository.findById(groupId));
    }
    public Optional<Group> getGroupByName(String groupName) {
        return Optional.of(groupRepository.findByGroupName(groupName));
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }


}
