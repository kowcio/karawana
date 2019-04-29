package karawana.repositories;


import karawana.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<Group, Long> {
	Group getOne(Long id);
	Optional<Group> findByGroupName(String groupName);

}


