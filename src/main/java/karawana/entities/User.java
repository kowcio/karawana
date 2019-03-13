package karawana.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="uid")
    private Long id = 0L;
    @Column(unique=true)
    private String name;
    @Version
    private Long version;
    @Max(999999)
    private int color;

    @CreatedDate
    private LocalDateTime createdDate;
    @Column(name = "group_id")
    private Long group_id;



    @Singular("location")
    @OrderBy("id")
    @OneToMany(cascade=CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")//by field name
    private List<Location> locations = new ArrayList<>();

    public User addLocation(Location location){
        locations.add(location);
        return this;
    }

}
