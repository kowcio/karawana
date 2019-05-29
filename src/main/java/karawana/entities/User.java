package karawana.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.web.PageableDefault;

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
@JsonInclude(JsonInclude.Include.NON_NULL)
@Proxy(lazy = false)
@PersistenceContext(type = PersistenceContextType.EXTENDED)
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
//    private Long group_id;


    //    @Singular("location")


//    @ManyToOne(cascade = CascadeType.ALL
//            , fetch = FetchType.EAGER)
//    public Group group;

    @OrderBy("id desc")
    @OneToMany(cascade = CascadeType.ALL
            , fetch = FetchType.LAZY
    )
    @JoinColumn(name = "userId")//, referencedColumnName="id")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private List<Location> locations = new ArrayList<>();

    @Getter
    @Column(name = "groupId")
    Long groupId;

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
                ", locations=" + locations +
                '}';
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public int getColor() {
        return color;
    }

//    public Long getId_grupy() {
//        return id_grupy;
//    }
//
//    public void setId_grupy(Long id_grupy) {
//        this.id_grupy = id_grupy;
//    }
//
//    public Group getGroup() {
//        return group;
//    }
//
//    public void setGroup(Group group) {
//        this.group = group;
//    }

    public void setColor(int color) {
        this.color = color;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
