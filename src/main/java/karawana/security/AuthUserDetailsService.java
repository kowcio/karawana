package karawana.security;

/**
 * Created by Burzanski on 13.02.2017.
 */
//@Repository
//public class AuthUserDetailsService implements UserDetailsService {
//    @Autowired
//    private UserRepository users;
//    private org.springframework.security.core.userdetails.User userdetails;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        karawana.entities.User user = getUserDetail(username);
//        userdetails = new org.springframework.security.core.userdetails.User(user.getName(), "", getAuthorities(user.getName()));
//        return userdetails;
//    }
//
//    public List<GrantedAuthority> getAuthorities(String name) {
//        List<GrantedAuthority> authList = new ArrayList<>();
//        if (name.equals("admin")) {
//            authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
//        } else {
//            authList.add(new SimpleGrantedAuthority("ROLE_USER"));
//        }
//        return authList;
//    }
//
//    private karawana.entities.User getUserDetail(String username) {
//        karawana.entities.User user = users.findByName(username);
//        return user;
//    }
//}

