package karawana.service;

import karawana.entities.Group;
import karawana.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GroupService {

    @Autowired
    static GroupRepository groupRepository;

    public static Group getGroupLocations(String groupName){

        return groupRepository.findById(groupName);


    }




}
