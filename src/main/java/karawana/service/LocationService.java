package karawana.service;

import karawana.entities.Location;
import karawana.repositories.LocationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

/**
 * Created by Kowcio on 2016-10-08.
 */

@Service
@Transactional
@Slf4j
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Location save(Location location) {
        if (location.getUserId().equals(null))
            log.info("Location is null {}", location.getUserId());
        return locationRepository.save(location);

    }

//    public Optional<Location> saveUserLocation(Location location) {
//            return Optional.of(locationRepository.save(location));
//
//    }


}
