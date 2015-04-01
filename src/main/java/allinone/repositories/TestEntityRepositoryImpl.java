package allinone.repositories;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import allinone.entities.TestEntity;

@Repository
public class TestEntityRepositoryImpl implements TestEntityRepositoryCustom {

	@PersistenceContext ( type = PersistenceContextType.EXTENDED)
	@Autowired
	private EntityManager	entityManager;
	
	@Autowired
	private EntityManagerFactory	e;

	@Override
	public TestEntity update(TestEntity t) {


		System.out.println("Trying to merge");
		entityManager = e.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(t);
		entityManager.getTransaction().commit();
		return t;

		// entityManager.close();

	}

}
