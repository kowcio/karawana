package allinone.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "ROLES")
public class Role extends AbstractEntity implements GrantedAuthority {
    
    private static final long serialVersionUID = 1L;
    
    @Column(name = "ROLE")
    private String            role;
    
    @Override
    public String getAuthority() {
        return this.role;
    }
    
}
