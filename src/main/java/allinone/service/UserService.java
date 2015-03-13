package allinone.service;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

import static org.fest.assertions.Assertions.assertThat;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import allinone.entities.UserEntity;
import allinone.repositories.UserRepository;

@Service
public class UserService implements UserDetailsService {
    
    private UserRepository repo;
    
    @Autowired
    public UserService(UserRepository repo) {
        this.repo = repo;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.err.println("USERDETAILS LOADING ");
        UserEntity user = repo.findByName(username);
        if (user == null) {
            return null;
        }
        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_USER");
        if (username.equals("admin")) {
            auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN");
        }
        String password = user.getPassword();
        return new org.springframework.security.core.userdetails.User(username, password, auth);
    }
    
    /**
     * Metoda ustawia datę ostatniego logowania użytkownika user na now().
     *
     * @param user
     */
    @Transactional
    public void updateLastLoginDate(Optional<UserEntity> user) {
        
        assertThat(user).isNotNull();
        
        user.ifPresent(u -> {
            u.setLastLoginDate(ZonedDateTime.now());
            repo.save(u);
        });
    }
}
