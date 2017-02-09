package karawana.repositories;


import karawana.entities.Group;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.ManagedBean;
import javax.transaction.Transactional;

@Repository
@Service
@ManagedBean
@Transactional
public interface GroupRepository extends JpaRepository<Group, Long> {
	Group findById(Long id);
	Group findByGroupName(String groupName);


}


