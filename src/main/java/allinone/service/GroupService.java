package allinone.service;

import allinone.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by U6041021 on 26/10/2016.
 */

@Service
public class GroupService {

    @Autowired
    GroupRepository groupRepository;

}
