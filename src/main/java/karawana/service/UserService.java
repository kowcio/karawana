package karawana.service;


import karawana.entities.User;
import karawana.repositories.LocationRepository;
import karawana.repositories.UserRepository;
import karawana.utils.TestObjectFabric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Kowcio on 2016-10-08.
 */

@Service
@Transactional
public class UserService {


    @Autowired
    UserRepository userRepository;
    @Inject
    LocationRepository locationRepository;

    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    public User getUserByName(String userName) {

        User byName = userRepository.findByName(userName);
        byName.setLocations(locationRepository.getTop10ByUserId(byName.getId()));
        return byName;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    //WEBFLUX TESTING

    private static List<User> users = Arrays.asList(
            TestObjectFabric.getUser(),
            TestObjectFabric.getUser(),
            TestObjectFabric.getUser(),
            TestObjectFabric.getUser()
    );


    public Flux<User> findAllRxTest() {
        //Simulate big list of data, streaming it every 2 second delay
        return Flux.fromIterable(this.users).delayElements(Duration.ofSeconds(2));
    }

    public Flux<User> findAllFromDB() {
        //Simulate big list of data, streaming it every 2 second delay
        return Flux.fromStream(userRepository.findAll().stream());
    }

    public Mono<User> findMonoUserByID() {
        //Simulate big list of data, streaming it every 2 second delay
        return Mono.just(TestObjectFabric.getUser("MonoUser"));
    }

}
