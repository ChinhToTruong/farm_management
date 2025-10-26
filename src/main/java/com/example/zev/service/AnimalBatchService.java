package com.example.zev.service;

import com.example.zev.entity.AnimalBatch;
import com.example.zev.entity.CropSeason;
import com.example.zev.entity.Location;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.AnimalBatchRepository;
import com.example.zev.repository.CropSeasonRepository;
import com.example.zev.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalBatchService extends CrudServiceImpl<AnimalBatch> {

    private final AnimalBatchRepository animalBatchRepository;
    private final LocationService locationService;
    private final CropSeasonService cropSeasonService;

    @Override
    public AnimalBatch create(AnimalBatch animalBatch) throws BusinessException {
        Location location = locationService.findById(animalBatch.getLocation().getId());
        CropSeason  cropSeason = cropSeasonService.findById(animalBatch.getSeason().getId());

        animalBatch.setLocation(location);
        animalBatch.setSeason(cropSeason);
        return animalBatchRepository.save(animalBatch);
    }
}
