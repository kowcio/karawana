package allinone.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import allinone.model.AbstractModel;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class User extends AbstractModel {

	String name;


}
