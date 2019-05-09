package karawana.config;

import karawana.service.RequestHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RequestRouter {
    @Bean
    RouterFunction<ServerResponse> groupRoutes(RequestHandler requestHandler) {
        return RouterFunctions
                .route(RequestPredicates
                                .GET("/groups"),
                        requestHandler::streamWeather);
    }


//    @Bean
//    public RouterFunction<ServerResponse> locationRoutes(LocationHandler locationHandler) {
//        return RouterFunctions.route(RequestPredicates.PUT("/locations/{id}").and(accept(APPLICATION_JSON)), locationHandler::save);
    }


