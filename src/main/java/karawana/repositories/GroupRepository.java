package karawana.repositories;


import karawana.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<Group, Long> {
	Group findById(Long id);
	Group findByGroupName(String groupName);


}


