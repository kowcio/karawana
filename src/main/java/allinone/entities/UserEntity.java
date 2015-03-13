package allinone.entities;

import java.time.ZonedDateTime;
import java.util.Date;
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

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;


@Entity
@Table(name="user")
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
	private Date createdDate;
	
	@LastModifiedDate
	@DateTimeFormat(style = "M-")
	private Date modifiedDate;
	
	@LastModifiedBy
	@ManyToOne
	private UserEntity modifiedBy;
	

    @Column(name = "lastLoginDate", nullable = true)
    private ZonedDateTime lastLoginDate;
    
    @Transient
    private Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	
	
	@Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", password=" + password + ", version=" + version
                + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", modifiedBy=" + modifiedBy
                + "]";
    }

    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public UserEntity getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(UserEntity modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public void setLastLoginDate(ZonedDateTime lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }
	
	
}
