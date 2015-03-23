package allinone.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;
import lombok.Getter;

import org.springframework.data.domain.Persistable;

@Data
@MappedSuperclass
public abstract class AbstractEntity implements Persistable<Long> {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue ( strategy = GenerationType.AUTO)
	// , generator = "seq_generator")
	protected Long	                  id=0L;

	@Version
	@Column ( name = "version")
	protected Integer	                  version;

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public boolean isNew() {
		return id != null ? true : false;
	}

}
