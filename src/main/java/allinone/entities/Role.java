package allinone.entities;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import org.springframework.security.core.GrantedAuthority;

@Data
@Builder
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "authorities")
public class Role implements GrantedAuthority {
    
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long            id               = 0L;
    
    @Column(name = "ROLE")
    private String            role;
    
    @Override
    public String getAuthority() {
        return this.role;
    }
    
}