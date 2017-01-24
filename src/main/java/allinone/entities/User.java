package allinone.entities;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
public class User //extends AbstractEntity
{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id = 0L;

	@Version
	private Long version;

	@Column(name = "username", unique = true)
	private String            name;

	@Column(name = "password")
	private String            password;

	@DateTimeFormat
	@CreatedDate
	private DateTime createdDate;

	@OneToMany(targetEntity=User.class, mappedBy="id", fetch=FetchType.LAZY)
	private List<Location> locations;






}
