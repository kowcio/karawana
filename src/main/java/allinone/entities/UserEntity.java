package allinone.entities;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "userEntity")
@Data
@EqualsAndHashCode(callSuper=false)
public class UserEntity extends AbstractEntity implements UserDetails {
    
    private static final long     serialVersionUID   = 1L;
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id")
    private Long                  id;
    
    @Column(unique = true)
    private String                name;
    
    @Column(name = "password")
    private String                password;
    
    @Version
    private Long                  version;
    
    @DateTimeFormat(style = "M-")
    @CreatedDate
    private DateTime              createdDate;
    
    @LastModifiedDate
    @DateTimeFormat(style = "M-")
    private DateTime              modifiedDate;
    
    @ManyToOne
    private TestEntity            testEntity;
    
    @Column(name = "lastLoginDate", nullable = true)
    private DateTime              lastLoginDate;
    
    @ManyToOne
    @JoinColumn(name = "grantedAuthorities", nullable = true)
    private Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
    
    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", version=" + version
                + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", modifiedBy="
                + testEntity.toString() + "]";
    }

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
    
}
