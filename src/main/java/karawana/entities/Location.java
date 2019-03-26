package karawana.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import java.time.LocalDateTime;


@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@JsonIgnoreProperties(ignoreUnknown=true,value={"hibernateLazyInitializer", "handler"})
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
    @Column(name = "USER_ID")
    private Long user_id;
    @CreatedDate
    private LocalDateTime createdDate;
    private String lat;
    private String lng;

}
