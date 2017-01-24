package allinone.service;

import allinone.entities.User;
import allinone.repositories.UserRepository;
import org.springframework.stereotype.Service;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 * Created by Kowcio on 2016-10-08.
 */

@Service
public class UserService {


    public static User getRandomUser() {
        PodamFactory factory = new PodamFactoryImpl();
        return factory.manufacturePojo(User.class);

    }

    public static User getUserByID(Long user){
        //get from repo
        return null;
    }

}
