package karawana.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


//@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@Proxy(lazy = false)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name="lid")
    private Long id = 0L;
    //    @NotNull
//    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinColumn(name="id")
//    @Column(name = "user_id")
    private Long user_id;
    @CreatedDate
    private LocalDateTime createdDate;
    private Double lat;
    private Double lng;

    public Object printCoords() {
        return "lat:" + lat + ",lng:" + lng;
    }
}
