package allinone.repositories;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

import allinone.entities.TestEntity;

@Cacheable("testEntities")
public interface TestEntityRepository extends JpaRepository<TestEntity, Long>, TestEntityRepositoryCustom {

}
