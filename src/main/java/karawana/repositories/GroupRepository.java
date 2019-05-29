package karawana.repositories;


import karawana.entities.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    Group getOne(Long id);


    @Query(nativeQuery = true, value = "SELECT TOP 10 * FROM GROUP_TABLE G ORDER BY G.ID DESC")
    List<Group> getTop10();

    Optional<Group> getOneById(Long id);
    Optional<Group> findByGroupName(String groupName);



//	@Query("select top 3 from location where userId in (select id from user where group_id = ?) order by date desc")
//	int findAllChildrenCount(@Param("groupId")Long groupId);



}


