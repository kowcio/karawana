package karawana.entities;

import karawana.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
@Table(name = "group_table")
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id = 0L;

    @Column(unique = true)
    private String groupName;
    @Version
    private Long version;
    private String password;

    @Transient
    @Autowired
    UserService userService;

    @OneToMany(targetEntity = User.class, mappedBy = "id", fetch = FetchType.LAZY)
    @ElementCollection
    List<User> users = new ArrayList<>(0);

    @DateTimeFormat
    @CreatedDate
    private LocalDateTime createdDate;

    private List<User> addUserToGroup(User user) {
        users.add(user);
        return users;
    }

    private List<User> addUserToGroup(Long userId) {
        users.add(userService.getUserById(userId));
        return users;
    }
//    private List<User> addUserToGroup(String name) {
//        users.add(UserService.getUserByID(user));
//        return users;
//    }

}
