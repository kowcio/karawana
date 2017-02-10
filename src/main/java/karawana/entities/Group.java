package karawana.entities;

import karawana.service.UserService;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Null;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "group_table")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(unique = true)
    private String groupName;
    @Version
    private Long version;
    private String password;

    @Transient
    @Autowired
    UserService userService;

    @OneToMany(cascade = CascadeType.ALL, targetEntity = User.class, mappedBy = "id", fetch = FetchType.LAZY)
    @ElementCollection
    List<User> users = new ArrayList<>(0);

    @DateTimeFormat
    @CreatedDate
    private LocalDateTime createdDate;


}
