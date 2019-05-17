package karawana.repositories;


import karawana.entities.Location;
import karawana.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface LocationRepository extends JpaRepository<Location, Long> {
    Location getOne(Long id);

    Location getTop10ById(Long id);
    List<Location> getTop5ByUserIdOrderByCreatedDateDesc(Long userId);
    List<Location> getTop10ByUserId(Long userId);




}


