package allinone.repositories;

import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import allinone.entities.TestEntity;

@Repository
@Transactional
public class TestEntityRepositoryImpl implements TestEntityRepositoryCustom {

	// @PersistenceContext ( type = PersistenceContextType.EXTENDED)
	@Autowired
	private EntityManager	entityManager;

	/**
	 * Transaction repo - methods inherit.
	 */
	@Override
	public TestEntity update(TestEntity t) {

		entityManager.merge(t);
		return t;

	}

}
