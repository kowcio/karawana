package allinone.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import allinone.entities.TestEntity;

@Repository
@Transactional
public interface TestEntityRepository extends JpaRepository<TestEntity, Long>{

	// method generatoed by reflection from method name - searching by name
	TestEntity findByTestStringLike(String testString);
}
