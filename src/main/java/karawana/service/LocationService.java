package karawana.service;

import karawana.entities.Location;
import karawana.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kowcio on 2016-10-08.
 */

@Service
public class LocationService {

    @Autowired
    LocationRepository locationRepository;
    private Location addUserLocation(Long userid, Location location){

        return locationRepository.save(location);
    }

    

}
