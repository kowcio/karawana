package allinone.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import allinone.entities.TestEntity;
import allinone.entities.UserEntity;

@Repository
public interface TestEntityRepository extends JpaRepository<TestEntity, Long> {
    
    //method generatoed by reflection from method name - searching by name
    TestEntity findByTestStringLike(String testString);
}


