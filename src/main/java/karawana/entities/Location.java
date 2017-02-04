package karawana.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Data
@Builder
@Entity

@NoArgsConstructor
@AllArgsConstructor
public class Location
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id = 0L;

    @Version
    private Long version;

    private Double latitude;
    private Double longitude;

}
