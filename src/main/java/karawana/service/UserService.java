package karawana.service;


import karawana.entities.User;
import karawana.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by Kowcio on 2016-10-08.
 */



@Service
public class UserService {


@Autowired
    UserRepository userRepository;

    public static User getRandomUser() {
        PodamFactory factory = new PodamFactoryImpl();
        return factory.manufacturePojo(User.class);

    }

    public static User getUserByID(Long user){

        return null;
    }

}
