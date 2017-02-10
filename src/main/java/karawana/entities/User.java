package karawana.entities;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
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
    private Long id = 0L;
    @NotNull
    private Long gid = 0L;

    @Column
    private String name;
    @Version
    private Long version;
    @Max(999999)
    private int color;

    @DateTimeFormat
    @CreatedDate
    private LocalDateTime createdDate;

    @OneToMany(cascade=CascadeType.ALL , targetEntity = Location.class, mappedBy = "uid", fetch = FetchType.LAZY)
    private List<Location> locations = new ArrayList<>(0);

}
