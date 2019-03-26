package karawana.service;


import karawana.entities.User;
import karawana.repositories.UserRepository;
import karawana.utils.TestObjectFabric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kowcio on 2016-10-08.
 */

@Service
@Transactional
public class UserService {


    @Autowired
    UserRepository userRepository;

    public User getRandomUser() {
       return TestObjectFabric.getUser("RandomTest");
    }

    public User getUserById(Long id) {
        return userRepository.getOne(id);
    }

    public User getUserByName(String userName) {
        return userRepository.findByName(userName);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }


    public static List<User> movie = new ArrayList<User>(){{
        add(TestObjectFabric.getUser("Flux 1"));
        add(TestObjectFabric.getUser("Flux 2"));
        add(TestObjectFabric.getUser("Flux 3"));
        add(TestObjectFabric.getUser("Flux 4"));
        add(TestObjectFabric.getUser("Flux 5"));

    }};

    public Flux<User> findAll() {
        return Flux.fromIterable(movie).delayElements(Duration.ofSeconds(2));
    }

}
