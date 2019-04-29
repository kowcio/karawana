package karawana.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
//@Data
@Builder
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Proxy(lazy = false)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name="uid")
    private Long id = 0L;
    private String name;
    @Version
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long version;
    @Max(999999)
    private int color;

    @CreatedDate
    private LocalDateTime createdDate = LocalDateTime.now();
//    @Column(name = "group_id", nullable = false)
//    @Column(nullable = false)
    private Long group_id;


    //    @Singular("location")
    @OrderBy("id desc")
    @OneToMany(cascade = CascadeType.ALL
            , fetch = FetchType.EAGER
    )
    @JoinColumn(name = "user_id")//by field name
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

    public List<Location> addLocation(Location locations) {
        this.locations.add(locations);
        return this.locations;
    }

    public User location(Location location) {
        locations.add(location);
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", version=" + version +
                ", color=" + color +
                ", createdDate=" + createdDate +
                ", group_id=" + group_id +
                ", locations=" + locations +
                '}';
    }
}
