package karawana.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.annotation.SessionScope;

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
//@SessionScope
@JsonIgnoreProperties(ignoreUnknown=true,value={"hibernateLazyInitializer", "handler"})
@Getter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@Column(name="uid")
    private Long id = 0L;
    public String name;
    @Version
    @Column(name = "optlock", columnDefinition = "integer DEFAULT 0", nullable = false)
    private Long version=0L;
    @Max(999999)
    public int color;

    @CreatedDate
    private LocalDateTime createdDate;
    @Column(name = "group_id")
    private Long group_id;



//    @Singular("location")
    @OrderBy("id")
    @OneToMany(cascade=CascadeType.ALL , fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")//by field name
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private List<Location> locations = new ArrayList<>();

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public User location(Location location){
        locations.add(location);
        return this;
    }

}
