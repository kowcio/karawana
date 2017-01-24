package allinone.entities;

import allinone.model.AbstractModel;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;


@Data
@Builder
@Entity
public class Location //extends AbstractEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id = 0L;

    @Version
    private Long version;

    private Double latitude;
    private Double longitude;



}
