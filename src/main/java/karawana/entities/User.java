package karawana.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
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
    @Column(name = "GROUP_ID")
    private Long user_id;



    @Singular("location")
    @OrderBy("id")
    @OneToMany(cascade=CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName ="id")//by field name
    private List<Location> locations = new ArrayList<>();

    public User addLocation(Location location){
        locations.add(location);
        return this;
    }

}
