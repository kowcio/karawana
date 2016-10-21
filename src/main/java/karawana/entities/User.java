package karawana.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import karawana.model.Location;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Entity
@Table(name = "USERS")
@Data
@EqualsAndHashCode(callSuper = false)
public class User implements UserDetails {

    private static final long serialVersionUID = 1L;

    @Column(name = "username", unique = true)
    private String name;

    @Column(name = "password")
    private String password;

    @Column
    private Location location;

    @DateTimeFormat(style = "M-")
    @CreatedDate
    private DateTime createdDate;

    @Column(name = "lastLoginDate", nullable = true)
    private DateTime lastLoginDate;

    /*TODO - Role sie przyda do stwierdzenia czy to hostCar czy userCar(follower) - wyjebalem z rozpędu ;)*/
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
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

    public static User getUser(String username, String pass) {
        User user = new User();
        user.name = username;
        user.password = pass;
        return user;
    }

    public User() {
    }

}
