package karawana.service;


import io.lettuce.core.resource.Delay;
import karawana.entities.Group;
import karawana.repositories.GroupRepository;
import karawana.repositories.ReactiveGroupRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.List;
import java.util.Optional;


@Service
public class GroupService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired(required = true)
    private GroupRepository groupRepository;

    @Autowired(required = true)
    private ReactiveGroupRepository reactiveGroupRepository;

    public Optional<Group> getGroupById(Long groupId) {
        return Optional.of(groupRepository.getOne(groupId));
    }

    public Optional<Group> getGroupByName(String groupName) {
        return groupRepository.findByGroupName(groupName);
    }

//    public Mono<Group> getOneReactive(Long id) {
//        return reactiveGroupRepository.findById(id).g;
//    }


    public Flux<Group> getTopGroupsReactive() {
        Flux<Group> groupFlux = Flux.fromIterable
                (reactiveGroupRepository.findTop15ByOrderByIdDesc())
                .delayElements(Duration.ofSeconds(2));
        return groupFlux;
    }

    //http://stackoverflow.com/questions/11881479/how-do-i-update-an-entity-using-spring-data-jpa
    public Group saveGroup(Group group) {
//        group
//                .getUsers()
//                .stream()
//                .filter(c->c.getGid()==null)
//                .forEach(c -> c.setGid(group.getId()))

        return groupRepository.save(group);
    }


}
