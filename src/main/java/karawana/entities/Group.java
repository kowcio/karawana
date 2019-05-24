package karawana.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Proxy;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.CascadeType.*;


//@Data
@Getter
@Setter
@Entity(name = "Group")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Table(name = "group_table")
@JsonSerialize
@JsonIgnoreProperties(ignoreUnknown = true)
@Proxy(lazy = true)
@PersistenceContext(type= PersistenceContextType.EXTENDED)
@ToString
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = 0L;

    @Column(unique = true)
    private String groupName;
    @Version
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long version;
    private String password;


    @OneToMany(cascade={
            PERSIST,
            MERGE, REMOVE, REFRESH, DETACH}
            , fetch = FetchType.EAGER
    )//, targetEntity = User.class, mappedBy = "gid", fetch = FetchType.LAZY)
    @OrderBy("id")
    @JoinColumn(name = "groupId")//, referencedColumnName="id")
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @Builder.Default
    private Set<User> users = new HashSet<>();

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Group addUser(User user) {
//        user.setGroup_id(this.id);
        this.users.add(user);
        return this;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void user(Set<User> users) {
        this.users = users;
    }

    @CreatedDate
    private LocalDateTime createdDate;


}
