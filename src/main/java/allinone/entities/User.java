package allinone.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

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
public class User extends AbstractAuditableEntity implements UserDetails {
    
    @Column(name = "username", unique = true)
    private String     name;
    
    @Column(name = "password")
    private String     password;
    
    @DateTimeFormat(style = "M-")
    @CreatedDate
    private DateTime   createdDate;
    
    @LastModifiedDate
    @DateTimeFormat(style = "M-")
    private DateTime   modifiedDate;
    
    @ManyToOne
    private TestEntity testEntity;
    
    @Column
    private Boolean    enabled;
    
    @Column(name = "lastLoginDate", nullable = true)
    private DateTime   lastLoginDate;
    
    @ManyToMany
    @Column(name = "USERS_GRANTED_AUTHORITIES ")
    private Set<Role>  grantedAuthorities = new HashSet<>();
    
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
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }
    
    @Override
    public boolean isEnabled() {
        // TODO Auto-generated method stub
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
        // TODO Auto-generated constructor stub
    }
    
    public User(String username, String password2, List<GrantedAuthority> auth) {
        // TODO Auto-generated constructor stub
    }
    
}
