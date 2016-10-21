package karawana.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder


public class Group {
    String name;
    String password;
    boolean isPasswordProtected;
    List<User> users = new ArrayList<>(0);

}
