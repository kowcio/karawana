package allinone.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.Data;

import org.springframework.data.domain.Persistable;

@Data
@MappedSuperclass
public abstract class AbstractEntity implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id = 0L;

    @Version
    @Column(name = "version")
    private Long version;

    public Long getId() {
        return id;
    }

    public boolean isNew() {
        boolean isNew = id != null;//? true : false;
        return isNew;
    }
}
