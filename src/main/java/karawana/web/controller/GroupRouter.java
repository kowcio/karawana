package karawana.web.controller.routers;

import karawana.repositories.GroupRepository;
import karawana.web.controller.handlers.GroupHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.server.WebSession;

import javax.inject.Inject;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@Controller
public class GroupRouter {

    @Autowired
    GroupHandler groupHandler;

    @Bean
    RouterFunction<ServerResponse> composedRoutes() {

        return route()
                .GET("/group/{id}", RequestPredicates.accept(APPLICATION_JSON),
                        c -> groupHandler.getGroupByIdRouter(c))
                .GET("/group2/{id}",
                        RequestPredicates.accept(APPLICATION_JSON),
                        c -> groupHandler.getPaths(c))

//                .GET("/person", RequestPredicates.accept(APPLICATION_JSON), GroupRouter::listPeople)
//                .POST("/person", GroupRouter::createPerson)
//                        .add(otherRoute)
                .build();
    }


}
