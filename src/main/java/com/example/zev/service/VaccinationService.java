package com.example.zev.service;

import com.example.zev.entity.AnimalBatch;
import com.example.zev.entity.User;
import com.example.zev.entity.Vaccination;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.VaccinationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VaccinationService extends CrudServiceImpl<Vaccination> {

    private final VaccinationRepository vaccinationRepository;
    private final UserService userService;
    private final AnimalBatchService animalBatchService;

    @Override
    public Vaccination create(Vaccination vaccination) throws BusinessException {
        User user = userService.findById(vaccination.getUser().getId());
        AnimalBatch animalBatch = animalBatchService.findById(vaccination.getAnimalBatch().getId());

        vaccination.setUser(user);
        vaccination.setAnimalBatch(animalBatch);
        return vaccinationRepository.save(vaccination);
    }
}
