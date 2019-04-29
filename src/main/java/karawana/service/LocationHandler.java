//package karawana.service;
//
//import karawana.entities.Group;
//import karawana.repositories.LocationRepository;
//import karawana.utils.TestObjectFabric;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.stereotype.Component;
//import org.springframework.web.reactive.function.server.ServerRequest;
//import org.springframework.web.reactive.function.server.ServerResponse;
//import reactor.core.publisher.Mono;
//
//@Component
//public class LocationHandler {
//    @Autowired
//    private LocationRepository locationRepository;
//
//    public Mono<ServerResponse> save(ServerRequest request) {
//        return ServerResponse.ok()
//                .contentType(MediaType.TEXT_EVENT_STREAM)
//                .body(
//                        .streamGroups(), Group.class);
//    }
//}