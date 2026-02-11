package com.example.zev.controller;

import com.example.zev.dto.response.ResponseData;
import com.example.zev.entity.Vaccination;
import com.example.zev.service.VaccinationService;
import java.io.IOException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vaccinations")
public class VaccinationController extends BaseController<Vaccination> {
    protected VaccinationService service;

    public VaccinationController(VaccinationService service){
        super(service);
        this.service = service;
    }

    @GetMapping("/export")
    public ResponseData<?> export() throws IOException {
        return ResponseData.builder()
            .data(service.exportExcel())
            .build();
    }
}
