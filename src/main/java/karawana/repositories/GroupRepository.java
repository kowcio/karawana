package karawana.repositories;


import karawana.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository

@Transactional
@PersistenceContext(type = PersistenceContextType.EXTENDED)
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group getOne(Long id);


    @Query(nativeQuery = true, value = "SELECT TOP 10 * FROM GROUP_TABLE G ORDER BY G.ID DESC")
    List<Group> getTop10();
    Optional<Group> getOneById(Long id);
    Optional<Group> findByGroupName(String groupName);


//    List<Group> getTop10();
//    @Query(nativeQuery = true, value = "SELECT Top 10 * FROM GROUP_TABLE G WHERE G.ID = :groupId ORDER BY G.ID DESC")
//    List<Group> findCommandStatusByVinOrderByCreatedTimestampDesc(@Param("groupId") String groupId);



//    @Query("Select t From group_table t where t.id=?1")
//    Group findByIdQuery(Long id);


}


