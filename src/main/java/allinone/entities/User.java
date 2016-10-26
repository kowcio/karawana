package allinone.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import allinone.model.AbstractModel;
import lombok.Builder;
import lombok.Data;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;

@Data
@Builder
public class User extends AbstractEntity {

	@Column(name = "username", unique = true)
	private String            name;

	@Column(name = "password")
	private String            password;

	@DateTimeFormat(style = "M-")
	@CreatedDate
	private DateTime createdDate;
}
