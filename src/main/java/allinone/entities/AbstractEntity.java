package allinone.entities;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import org.springframework.data.domain.Persistable;

@Data
@MappedSuperclass
public class AbstractEntity implements Persistable<Long> {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id = 0L;

    @Version
    private Long version;

    public Long getId() {
        return id;
    }

    public boolean isNew() {
        boolean isNew = id != null;//? true : false;
        return isNew;
    }

    public AbstractEntity() {
    }
}
