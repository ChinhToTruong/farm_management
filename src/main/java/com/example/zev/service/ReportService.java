package com.example.zev.service;

import com.example.zev.entity.*;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.ReportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReportService extends CrudServiceImpl<Report> {

    private final ReportRepository reportRepository;
    private final UserService userService;
    private final PlantService plantService;
    private final CropSeasonService cropSeasonService;
    private final AnimalBatchService animalBatchService;

    @Override
    public Report create(Report report) throws BusinessException {
        User user = userService.findById(report.getUser().getId());
        CropSeason  cropSeason = cropSeasonService.findById(report.getCropSeason().getId());

        if (report.getBatch() != null) {
            AnimalBatch animalBatch = animalBatchService.findById(report.getBatch().getId());
            report.setBatch(animalBatch);
        }
        
        if (report.getPlant() != null) {
            Plant plant = plantService.findById(report.getPlant().getId());
            report.setPlant(plant);
        }

        report.setCropSeason(cropSeason);
        report.setUser(user);
        return super.create(report);
    }
}
