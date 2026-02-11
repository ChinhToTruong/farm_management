package com.example.zev.service;

import com.example.zev.entity.AnimalBatch;
import com.example.zev.entity.User;
import com.example.zev.entity.Vaccination;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.VaccinationRepository;
import com.example.zev.utils.AuthUtils;
import com.example.zev.utils.ExportExcelUtils;
import java.io.IOException;
import java.util.List;
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
        User user = userService.findEntity(vaccination.getUserId());
        AnimalBatch animalBatch = animalBatchService.findById(vaccination.getBatchId());
//
//        vaccination.setUser(user);
//        vaccination.setAnimalBatch(animalBatch);
        return vaccinationRepository.save(vaccination);
    }

    public String exportExcel() throws IOException {
        Long userId = AuthUtils.getUserId();
        List<Object[]> rows = vaccinationRepository.findByUserId(userId);
        List<String> headers = List.of(
            "Tên vắc xin",
            "Ngày bắt đầu",
            "Ngày tiêm tiếp theo",
            "Ghi chú",
            "Người thực hiện",
            "Tên đàn",
            "Trạng thái"
        );

        return ExportExcelUtils.exportExcelBase64("Đợt tiêm phòng", headers, rows);
    }
}
