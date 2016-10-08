package allinone.model;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
@Builder

public class Group extends AbstractModel {

    List<User> users = new ArrayList<>(0);


}
