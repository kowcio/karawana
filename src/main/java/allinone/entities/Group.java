package allinone.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;


@Data
@Builder
@Entity
public class Group extends AbstractEntity {

    @OneToMany(targetEntity = Group.class, mappedBy = "id", fetch = FetchType.LAZY)
    List<Group> users = new ArrayList<>(0);

}
