package allinone.entities;

import allinone.model.AbstractModel;
import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;


@Data
@Builder
@Entity
public class Location extends AbstractEntity {
    String name;
}
