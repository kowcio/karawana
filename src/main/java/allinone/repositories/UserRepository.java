package allinone.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import allinone.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
}


