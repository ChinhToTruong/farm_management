package com.example.zev.service;

import com.example.zev.entity.CropSeason;
import com.example.zev.entity.Location;
import com.example.zev.repository.CropSeasonRepository;
import com.example.zev.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CropSeasonService extends CrudServiceImpl<CropSeason> {

    private final LocationRepository locationRepository;
    private final CropSeasonRepository cropSeasonRepository;



    @Override
    public CropSeason create(CropSeason cropSeason) {
        Location location = locationRepository.findById(cropSeason.getLocation().getId()).get();
        cropSeason.setLocation(location);
        return cropSeasonRepository.save(cropSeason);
    }
}
