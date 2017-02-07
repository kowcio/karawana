package karawana.service;


import karawana.entities.Group;
import karawana.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
@Transactional
public class GroupService {

    @Autowired(required = true)
    private GroupRepository groupRepository;

    public Group getGroupLocations(String groupName) {
        return groupRepository.findById(groupName);
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }


}
