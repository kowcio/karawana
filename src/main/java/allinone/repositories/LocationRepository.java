package allinone.repositories;


import allinone.entities.Location;
import allinone.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {
	User findByName(String name);
}


