package karawana.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "USERS")
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements UserDetails {
    
    private static final long serialVersionUID   = 1L;
    
    @Column(name = "username", unique = true)
    private String            name;
    
    @Column(name = "password")
    private String            password;
    
    @DateTimeFormat(style = "M-")
    @CreatedDate
    private DateTime          createdDate;
    
    @LastModifiedDate
    @DateTimeFormat(style = "M-")
    private DateTime          modifiedDate;

    @ManyToOne
    private TestEntity        testEntity;

    @Column
    private Boolean           enabled;
    
    @Column(name = "lastLoginDate", nullable = true)
    private DateTime          lastLoginDate;
    
    @ManyToMany
    @Column(name = "USERS_GRANTED_AUTHORITIES ")
    private Set<Role>         grantedAuthorities = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }
    
    @Override
    public String getUsername() {
        return name;
    }
    
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        return true;
    }
    
    public static User getUser(String username, String pass, Set<Role> authorities) {
        User user = new User();
        user.name = username;
        user.password = pass;
        user.grantedAuthorities = authorities;
        return user;
    }
    
    public User() {
    }
    
}
