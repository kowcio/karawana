package allinone.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table ( name = "testEntity")
@Data
@EqualsAndHashCode ( callSuper = true)
@ToString ( callSuper = true, includeFieldNames = true)
@Builder
@AllArgsConstructor
@EntityListeners ( AuditingEntityListener.class)
public class TestEntity extends AbstractEntity {

	@Transient
	private static final long	 serialVersionUID	= 1L;

	@Column ( length = 255)
	private String	             testString;

	private Long	             testLong;

	@Column ( length = 255)
	private String	             testSomeStringDate;

	@DateTimeFormat
	@CreatedDate
	@Type ( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime	         createdDate;

	@LastModifiedDate
	@DateTimeFormat
	@Type ( type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
	private DateTime	         modifiedDate;

	public TestEntity() {
		super();
	}

	public TestEntity(Long id) {
		this.id = id;
	}

}
