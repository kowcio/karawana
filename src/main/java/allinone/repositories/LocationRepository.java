package allinone.repositories;


import allinone.entities.Location;
import allinone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Location, Long> {
	User findById(String name);
}


