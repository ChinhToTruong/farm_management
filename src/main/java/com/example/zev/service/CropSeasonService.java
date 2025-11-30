package com.example.zev.service;

import com.example.zev.constants.ErrorCode;
import com.example.zev.dto.request.SearchRequest;
import com.example.zev.entity.CropSeason;
import com.example.zev.entity.Location;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.CropSeasonRepository;
import com.example.zev.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CropSeasonService extends CrudServiceImpl<CropSeason> {

    private final LocationRepository locationRepository;
    private final CropSeasonRepository cropSeasonRepository;



    @Override
    public CropSeason create(CropSeason cropSeason) throws BusinessException {
        Location location = locationRepository.findById(cropSeason.getLocationId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
        cropSeason.setLocation(location);
        cropSeason.setLocationId(cropSeason.getLocation().getId());
        return cropSeasonRepository.save(cropSeason);
    }

    @Override
    public CropSeason findById(Long id) throws BusinessException {
        CropSeason cropSeason = super.findById(id);
        Location location = locationRepository.findById(cropSeason.getLocationId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
        cropSeason.setLocation(location);
        return cropSeason;
    }
}
