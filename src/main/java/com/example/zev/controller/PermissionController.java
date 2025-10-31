package com.example.zev.controller;

import com.example.zev.entity.Permission;
import com.example.zev.service.CrudService;
import com.example.zev.service.PermissionService;
import com.example.zev.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permissions")
public class PermissionController extends BaseController<Permission>{
    protected PermissionService service;
    protected PermissionController(PermissionService service) {
        super(service);
        this.service = service;
    }
}
