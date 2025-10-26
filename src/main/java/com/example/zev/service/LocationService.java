package com.example.zev.service;

import com.example.zev.constants.ErrorCode;
import com.example.zev.entity.Location;
import com.example.zev.entity.User;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.LocationRepository;
import com.example.zev.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class LocationService extends CrudServiceImpl<Location> {

    private final UserRepository userRepository;
    private final LocationRepository locationRepository;

    public LocationService(UserRepository userRepository, LocationRepository locationRepository) {
        this.userRepository = userRepository;
        this.locationRepository = locationRepository;
    }


    @Override
    public Location create(Location location) throws BusinessException {
        User user = userRepository.findById(location.getUser().getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));

        location.setUser(user);
        return locationRepository.save(location);
    }
}
