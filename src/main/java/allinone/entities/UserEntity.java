package allinone.entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

import lombok.Data;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name="userEntity")
@Data
public class UserEntity {

	private static final long serialVersionUID = 1L;
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Id
	@Column(name = "id")
	private Long id;
	
	@Column(unique=true)
	private String name;
	
	@Column(name="password")
	private String password;
	
	@Version
	private Long version;
	
	@DateTimeFormat(style = "M-")
	@CreatedDate
	private DateTime createdDate;
	
	@LastModifiedDate
	@DateTimeFormat(style = "M-")
	private DateTime modifiedDate;
	
	@ManyToOne
	private TestEntity testEntity;
	

    @Column(name = "lastLoginDate", nullable = true)
    private DateTime lastLoginDate;
    
    @Transient
    private Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	
	
	@Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", version=" + version
                + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", modifiedBy=" + testEntity.toString()
                + "]";
    }


	
}
