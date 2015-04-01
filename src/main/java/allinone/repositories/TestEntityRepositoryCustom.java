package allinone.repositories;

import org.springframework.stereotype.Repository;

import allinone.entities.TestEntity;

public interface TestEntityRepositoryCustom {
    
    
    TestEntity update(TestEntity t);
    
}
