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
    private static GroupRepository groupRepository;

    public static Group getGroupLocations(String groupName) {

        return groupRepository.findById(groupName);
    }

    public static Group saveGroup(Group group) {

        return groupRepository.save(group);

    }


}
