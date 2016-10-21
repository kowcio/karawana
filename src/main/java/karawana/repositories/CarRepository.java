package karawana.repositories;

import karawana.entities.User;
import karawana.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by U6041021 on 21/10/2016.
 */
public interface CarRepository extends JpaRepository<Car, Long> {
}
