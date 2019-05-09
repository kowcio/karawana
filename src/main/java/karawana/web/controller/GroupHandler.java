package karawana.web.controller.handlers;

import karawana.entities.Group;
import karawana.repositories.GroupRepository;
import karawana.web.controller.SESSION_VAR;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;

import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
@Slf4j
public class GroupHandler {

    @Inject
    private GroupRepository groupRepository;


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
