package karawana.service;


import io.lettuce.core.resource.Delay;
import karawana.entities.Group;
import karawana.repositories.GroupRepository;
import karawana.repositories.LocationRepository;
import karawana.repositories.ReactiveGroupRepository;
import karawana.utils.TestObjectFabric;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class GroupService {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired(required = true)
    private GroupRepository groupRepository;

    @Autowired
    private LocationRepository locationRepository;

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


    //http://stackoverflow.com/questions/11881479/how-do-i-update-an-entity-using-spring-data-jpa
    public Group saveGroup(Group group) {
//        group
//                .getUsers()
//                .stream()
//                .filter(c->c.getGid()==null)
//                .forEach(c -> c.setGid(group.getId()))

        return groupRepository.save(group);
    }


    public Flux<Group> streamGroups(
    ) {
        Flux<Long> interval = Flux.interval(Duration.ofSeconds(1));
        Flux<Group> events =
                Flux.fromStream(Stream.generate(
                        () -> TestObjectFabric
                                .getGroupWithOneUser(
                                        LocalDateTime.now().toString())));
        return Flux.zip(events, interval, (Group key, Long value) -> key);


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

    public Group getGrouptWith10LatestLocations(Long groupId) {
        Group one = groupRepository
                .getOne(groupId);
        one
                .getUsers()
                .forEach(u -> u.setLocations(locationRepository.getTop10ByUserIdOrderByCreatedDateDesc(u.getId())));
        return one;

    }


}
