package com.example.zev.service;

import com.example.zev.entity.Role;
import com.example.zev.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleService extends CrudServiceImpl<Role> {
}
