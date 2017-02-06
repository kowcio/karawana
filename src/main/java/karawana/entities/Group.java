package karawana.entities;

import karawana.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String groupName;
    @Version
    private Long version;
    private String password;

    @OneToMany(targetEntity = User.class, mappedBy = "id", fetch = FetchType.LAZY)
    List<User> users = new ArrayList<>(0);

    @DateTimeFormat
    @CreatedDate
    private LocalDateTime createdDate;

    private List<User> addUserToGroup(User user) {
        users.add(user);
        return users;
    }

    private List<User> addUserToGroup(Long user) {
        users.add(UserService.getUserByID(user));
        return users;
    }
//    private List<User> addUserToGroup(String name) {
//        users.add(UserService.getUserByID(user));
//        return users;
//    }

}
