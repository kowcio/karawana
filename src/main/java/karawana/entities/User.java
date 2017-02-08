package karawana.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = 0L;

    @Column
    private String name;
    @Version
    private Long version;
    @Max(999999)
    private int color;

    @DateTimeFormat
    @CreatedDate
    private LocalDateTime createdDate;

    @OneToMany(cascade=CascadeType.ALL , targetEntity = Location.class, mappedBy = "userId", fetch = FetchType.LAZY)
    private List<Location> locations;

}
