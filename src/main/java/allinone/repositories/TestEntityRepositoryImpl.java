package allinone.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import allinone.entities.TestEntity;

@Repository
public class TestEntityRepositoryImpl implements TestEntityRepository{
    
    
    @PersistenceContext
    private EntityManager entityManager;
    
    public void update(TestEntity test) {
    }
    
    @Override
    public void deleteInBatch(Iterable<TestEntity> arg0) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public List<TestEntity> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public List<TestEntity> findAll(Sort arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public List<TestEntity> findAll(Iterable<Long> arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public void flush() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public TestEntity getOne(Long arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public <S extends TestEntity> List<S> save(Iterable<S> arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public <S extends TestEntity> S saveAndFlush(S arg0) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public Page<TestEntity> findAll(Pageable pageable) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public <S extends TestEntity> S save(S entity) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public TestEntity findOne(Long id) {
        // TODO Auto-generated method stub
        return null;
    }
    
    @Override
    public boolean exists(Long id) {
        // TODO Auto-generated method stub
        return false;
    }
    
    @Override
    public long count() {
        // TODO Auto-generated method stub
        return 0;
    }
    
    @Override
    public void delete(Long id) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void delete(TestEntity entity) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void delete(Iterable<? extends TestEntity> entities) {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void deleteAll() {
        // TODO Auto-generated method stub
        
    }
    
    @Override
    public void deleteAllInBatch() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void update() {
        // TODO Auto-generated method stub
        
    }
}
