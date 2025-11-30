package com.example.zev.service;

import com.example.zev.entity.CropSeason;
import com.example.zev.entity.Location;
import com.example.zev.entity.Plant;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.PlantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlantService extends CrudServiceImpl<Plant> {

    private final LocationService locationService;
    private final CropSeasonService cropSeasonService;

    private final PlantRepository plantRepository;

    @Override
    public Plant create(Plant plant) throws BusinessException {
        Location location = locationService.findById(plant.getLocationId());
        CropSeason cropSeason = cropSeasonService.findById(plant.getCropSeasonId());
        plant.setLocation(location);
        plant.setCropSeason(cropSeason);
        return super.create(plant);
    }
}
