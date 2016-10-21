package karawana.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import karawana.entities.User;
import karawana.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepository      repo;
    
    /*
     * the framework will automaticaly check the user data os userDetails
     * ilplementation methods for validating user, need to add ecnoder TODO
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("USERDETAILS LOADING name = {}", username);
        User user = repo.findByName(username);
        if (user == null) {
            log.info("No users has been found in db");
            return null;
        }
        // this is the user we are returning after checking the username rest
        // does spring

        String password = user.getPassword();
        return User.getUser(username, password);
    }
    
    /**
     * Metoda ustawia datę ostatniego logowania użytkownika user na now().
     *
     * @param user
     */

    /*
     * @Transactional public void updateLastLoginDate(Optional<UserEntity> user)
     * {
     * 
     * assertThat(user).isNotNull();
     * 
     * user.ifPresent(u -> { u.setLastLoginDate(ZonedDateTime.now());
     * repo.save(u); }); }
     */
}
