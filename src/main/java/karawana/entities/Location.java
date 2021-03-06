package karawana.entities;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonInclude(JsonInclude.Include.NON_NULL)
//@Proxy(lazy=true)
//@PersistenceContext(type= PersistenceContextType.EXTENDED)
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   // @Column(name="lid")
    private Long id = 0L;
//    @NotNull
//    @ManyToOne
//    @JoinColumn(name="id")
    @Version
    private Long version;


//    @Column(name = "USER_ID")
    private Long userId;
    @CreatedDate
    private LocalDateTime createdDate;
    private Double lat;
    private Double lng;

    public Object printCoords() {
        return "lat:" + lat + ",lng:" + lng;
    }
}
