package karawana.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User {
    String name;
    String password;

    public User(String name) {
        this.name = name;
    }


}
