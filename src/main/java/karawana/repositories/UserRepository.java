package karawana.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import karawana.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByName(String name);
	User findByNameAndPassword(String name,String password);
}


