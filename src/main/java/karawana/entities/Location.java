package karawana.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;


//@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Proxy(lazy = false)
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name="lid")
    private Long id = 0L;
    //    @NotNull
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="id")
//    @Column(name = "user_id")
//    private Long user_id;

    //    @ManyToOne//(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @EmbeddedId
//    @Getter
//    public User user;
    @Getter
    @Setter
    @Column(name = "userId")
    Long userId;

    @CreatedDate
    private LocalDateTime createdDate;
    private Double lat;
    private Double lng;

    public Object printCoords() {
        return "lat:" + lat + ",lng:" + lng;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", createdDate=" + createdDate +
                ", lat=" + lat +
                ", lng=" + lng +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

}
