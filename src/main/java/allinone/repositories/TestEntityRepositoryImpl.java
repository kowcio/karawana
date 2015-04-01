package allinone.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import allinone.entities.TestEntity;

@Repository
public class TestEntityRepositoryImpl implements TestEntityRepositoryCustom {
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    
    @Override
    public TestEntity update(TestEntity t) {
        System.out.println("Trying to merge");
        
        entityManager.merge(t);
        entityManager.flush();
        return t;
        
        // entityManager.close();
        
    }
    
}
