package skele.repositories;


import javax.annotation.ManagedBean;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import skele.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}


