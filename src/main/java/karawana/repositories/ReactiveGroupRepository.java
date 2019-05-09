package karawana.repositories;


import karawana.entities.Group;
import karawana.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.transaction.Transactional;
import java.util.List;

@Service
public interface ReactiveGroupRepository extends JpaRepository<Group, Long> {

    //    @Query("SELECT TOP (:tops) * FROM Group_table")
//    @Query("select top 10 * from Group_table u")
//    List<Group> findTop10ById();
    List<Group> findTop15ByOrderByIdDesc();

}


