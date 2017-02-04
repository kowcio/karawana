package karawana.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
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
    protected Long id = 0L;

    @Version
    private Long version;

    @Column(name = "username", unique = true)
    private String name;

    @Column(name = "password")
    private String password;

    @DateTimeFormat
    @CreatedDate
    private LocalDateTime createdDate;

    @OneToMany(targetEntity = User.class, mappedBy = "id", fetch = FetchType.LAZY)
    private List<Location> locations;

}
