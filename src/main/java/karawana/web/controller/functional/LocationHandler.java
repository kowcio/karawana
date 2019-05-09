package karawana.web.controller.functional;

import karawana.entities.Location;
import karawana.repositories.LocationRepository;
import karawana.web.controller.SESSION_VAR;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import java.time.Duration;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Service
@Slf4j
public class LocationHandler {

    @Inject
    private LocationRepository locationRepository;

    @Autowired
    public LocationHandler(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }


    public Mono<ServerResponse> getGroupByIdRouter(ServerRequest request) {

        Object reqAttr = request.pathVariable("group_id");
        log.info("qwqweqwe {}", reqAttr);
        Long arg = request.session().block().getAttribute(SESSION_VAR.GROUP_ID);
        log.info("Should return 10 latests locations for group : {}", arg);
        List<Location> locations = locationRepository.getTop5ByUserIdOrderByCreatedDateDesc(arg);
        Flux<Location> people = Flux.fromIterable(locations).delayElements(Duration.ofSeconds(1));
        return ok().contentType(APPLICATION_JSON).body(people, Location.class);
    }

}
