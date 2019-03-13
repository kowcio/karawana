package karawana.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "group_table")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="gid")
    private Long id = 0L;

    @Column(unique = true)
    private String groupName;
    @Version
    private Long version;
    private String password;

    @OneToMany(cascade = CascadeType.ALL)//, targetEntity = User.class, mappedBy = "gid", fetch = FetchType.LAZY)
    @OrderBy("id")
    @Singular("user")
    @JoinColumn(name = "group_id")//, referencedColumnName = "uid")//by field name
//    @CollectionTable
    private Set<User> users = new HashSet<>();

    @CreatedDate
    private LocalDateTime createdDate;

    public Group addUser(User user){
        users.add(user);
        return this;
    }
}
