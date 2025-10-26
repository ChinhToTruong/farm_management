package com.example.zev.controller;

import com.example.zev.entity.Plant;
import com.example.zev.service.PlantService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/plants")
public class PlantController extends BaseController<Plant> {

    protected PlantService service;


    protected PlantController(PlantService service) {
        super(service);
        this.service = service;
    }
}
