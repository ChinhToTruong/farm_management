package com.example.zev.service;

import com.example.zev.entity.*;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.WorkDiaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WorkDiaryService extends CrudServiceImpl<WorkDiary> {

    private final UserService userService;
    private final WorkDiaryRepository workDiaryRepository;
    private final PlantService plantService;
    private final CropSeasonService cropSeasonService;
    private final AnimalBatchService animalBatchService;

    @Override
    public WorkDiary create(WorkDiary entity) throws BusinessException {
        User user = userService.findEntity(entity.getUser().getId());
        CropSeason cropSeason = cropSeasonService.findById(entity.getCropSeason().getId());
        if (entity.getPlant() != null){
            Plant plant = plantService.findById(entity.getPlant().getId());
            entity.setPlant(plant);
        }

        if (entity.getBatch() != null){
            AnimalBatch animalBatch = animalBatchService.findById(entity.getBatch().getId());
            entity.setBatch(animalBatch);
        }
        entity.setCropSeason(cropSeason);
        entity.setUser(user);

        return super.create(entity);
    }
}
