package com.example.zev.controller;

import com.example.zev.entity.Role;
import com.example.zev.service.CrudService;
import com.example.zev.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/roles")
public class RoleController extends BaseController<Role> {
    protected RoleService service;


    protected RoleController(RoleService service) {
        super(service);
        this.service = service;
    }
}
