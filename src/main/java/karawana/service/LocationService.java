package karawana.service;

import karawana.entities.Location;
import karawana.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * Created by Kowcio on 2016-10-08.
 */

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;

    public Optional<Location> saveUserLocation(Location location) {
            return Optional.of(locationRepository.save(location));

    }


}
