package karawana.web.roles;

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
