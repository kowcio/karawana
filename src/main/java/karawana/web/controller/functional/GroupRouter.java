package karawana.web.controller.functional;

import karawana.entities.Group;
import karawana.entities.User;
import karawana.repositories.GroupRepository;
import karawana.repositories.LocationRepository;
import karawana.web.controller.SESSION_VAR;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Transient;
import javax.transaction.Transactional;
import java.util.Optional;
import java.util.Set;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Slf4j
@Controller
@Transactional
public class GroupRouter {

    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    GroupHandler groupHandler = new GroupHandler(groupRepository);
    @Autowired
    LocationHandler locationHandler = new LocationHandler(locationRepository);

    @Bean
    RouterFunction<ServerResponse> composedRoutes() {

        return route()
                .GET("/group/{id}", RequestPredicates.accept(APPLICATION_JSON),
                        c -> groupHandler.getGroupByIdRouter(c))
                .GET("/group2/{id}",
                        RequestPredicates.accept(APPLICATION_JSON),
                        c -> getPaths(c))
                .GET("/locations/{group_id}",
                        RequestPredicates.accept(APPLICATION_JSON),
                        c -> locationHandler.getGroupByIdRouter(c))

//                .GET("/person", RequestPredicates.accept(APPLICATION_JSON), GroupRouter::listPeople)
//                .POST("/person", GroupRouter::createPerson)
//                        .add(otherRoute)
                .build();
    }

    public Mono<ServerResponse> getGroupByIdRouter(ServerRequest request) {
        log.info("qwqweqwe {}", request.attribute(SESSION_VAR.GROUP_ID));
        Group one = groupRepository.getOne(1L);
        Mono<Group> people = Mono.just(one);
        log.info("one {}", one);
        return ok().contentType(APPLICATION_JSON).body(people, Group.class);
    }


    @Transactional
    public Mono<ServerResponse> getPaths(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        Group oneById = groupRepository.getOne(id);


        Mono<Group> people = Mono.just(oneById);
        oneById.getUsers().forEach(c -> log.info("Location size :{},", c.getLocations().size()));
        log.info("one:{}, webSessionIDRouter:{}", oneById, request.session().block().getId());
        return ok().contentType(APPLICATION_JSON).body(people, Group.class);
    }


}
