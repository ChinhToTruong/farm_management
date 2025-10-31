package com.example.zev.service;

import com.example.zev.constants.ErrorCode;
import com.example.zev.entity.Permission;
import com.example.zev.entity.Role;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.PermissionRepository;
import com.example.zev.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RoleService extends CrudServiceImpl<Role> {

//    private final RoleRepository roleRepository;
//    private final PermissionRepository permissionRepository;
//    @Override
//    @Transactional
//    public Role create(Role entity) {
//        Role role = roleRepository.save(entity);
//        Set<Long> permissions = entity.getPermissions()
//                .stream().map(Permission::getId).collect(Collectors.toSet());
//        role.setPermissions();
//    }
}
