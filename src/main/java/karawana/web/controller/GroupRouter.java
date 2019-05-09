package karawana.web.controller.routers;

import karawana.entities.Group;
import karawana.repositories.GroupRepository;
import karawana.web.controller.SESSION_VAR;
import karawana.web.controller.handlers.GroupHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

import java.util.Optional;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
@Slf4j
@Controller
public class GroupRouter {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    GroupHandler groupHandler = new GroupHandler(groupRepository);

    @Bean
    RouterFunction<ServerResponse> composedRoutes() {

        return route()
                .GET("/group/{id}", RequestPredicates.accept(APPLICATION_JSON),
                        c -> groupHandler.getGroupByIdRouter(c))
                .GET("/group2/{id}",
                        RequestPredicates.accept(APPLICATION_JSON),
                        c -> getPaths(c))

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

    public Mono<ServerResponse> getPaths(ServerRequest request) {
        Long id = Long.valueOf(request.pathVariable("id"));
        final Optional<Group> oneById = groupRepository.getOneById(id);
        Group one = oneById.get();
        Mono<Group> people = Mono.just(one);
        log.info("one:{}, webSessionIDRouter:{}", one, request.session().block().getId());
        return ok().contentType(APPLICATION_JSON).body(people, Group.class);
    }


}
