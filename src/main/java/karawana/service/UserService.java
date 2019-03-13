package karawana.service;


import karawana.entities.User;
import karawana.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

import javax.transaction.Transactional;

/**
 * Created by Kowcio on 2016-10-08.
 */

@Service
@Transactional
public class UserService {


@Autowired
    UserRepository userRepository;

    public  User getRandomUser() {
        PodamFactory factory = new PodamFactoryImpl();
        return factory.manufacturePojo(User.class);

    }
    public User getUserById(Long id){
        return userRepository.getOne(id);
    }

    public  User saveUser(User user){
       return userRepository.save(user);
    }

}
