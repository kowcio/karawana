package allinone.web.roles;

import org.springframework.security.core.GrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public enum Authority {
    ROLE_ADMINISTRATION,
    ROLE_CUSTOM
 
/*
    public static Set<GrantedAuthority> getAll() {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

        Arrays.stream(Authority.values()).forEach(
                auth -> grantedAuthorities.add(GroupAuthorityEntity.get(GroupEntity.get("ADMINS"), auth))
        );

        return grantedAuthorities;
    }*/
}
