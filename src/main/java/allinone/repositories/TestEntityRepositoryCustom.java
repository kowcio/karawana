package allinone.repositories;

import org.springframework.stereotype.Repository;

import allinone.entities.TestEntity;

public interface TestEntityRepositoryCustom {
    
    public void update();
    
    void update(TestEntity t);
    
}
