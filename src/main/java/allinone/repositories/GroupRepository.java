package allinone.repositories;


import allinone.entities.Group;
import allinone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
	Group findById(String id);
}


