package allinone.service;

import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.fest.assertions.Assertions.assertThat;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import allinone.entities.Role;
import allinone.entities.User;
import allinone.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    private static final Logger log = LoggerFactory.getLogger(UserService.class);
    
    @Autowired
    private UserRepository repo;
        
    //the framework will authomaticaly check the user data os userDetails ilplementation methods
    //for validating user, need to add ecnoder TODO
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("USERDETAILS LOADING name = {}", username);
        User user = repo.findByName(username);
        if (user == null) {
            return null;
        }
        
        List<Role> auth = Arrays.asList(Role.builder().role("ROLE_USER").build());        
        String password = user.getPassword();
        
        //this is the user we are returning after checking the username rest does spring
        
        return  User.getUser(username, password,  new HashSet<>(auth));
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
