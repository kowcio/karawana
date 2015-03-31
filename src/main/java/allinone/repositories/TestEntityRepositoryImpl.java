package allinone.repositories;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.stereotype.Repository;

import allinone.entities.TestEntity;

@Repository
public class TestEntityRepositoryImpl implements TestEntityRepositoryCustom {
    
    @PersistenceContext(type = PersistenceContextType.EXTENDED)
    private EntityManager entityManager;
    
    @Override
    public void update(TestEntity t) {
        
        entityManager.merge(t);
        
        
        //entityManager.close();
        
        
    }
    
    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }
    
}
