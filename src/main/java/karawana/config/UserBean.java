package karawana.config;


import karawana.entities.Group;
import karawana.entities.User;
import karawana.utils.TestObjectFabric;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
@AllArgsConstructor
public class UserBean {

    private User user;
    private Group group;

    public UserBean() {
        this.user = TestObjectFabric.getUser();
    }
}
