package allinone.repositories;


import allinone.entities.UserOld;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<UserOld, Long> {
	UserOld findByName(String name);
	UserOld findByNameAndPassword(String name, String password);
}


