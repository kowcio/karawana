package allinone.entities;

import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Data
@Builder
public class User extends AbstractEntity {

	@Column(name = "username", unique = true)
	private String            name;

	@Column(name = "password")
	private String            password;

	@DateTimeFormat
	@CreatedDate
	private DateTime createdDate;
}
