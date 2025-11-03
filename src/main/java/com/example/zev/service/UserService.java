package com.example.zev.service;

import com.example.zev.constants.ErrorCode;
import com.example.zev.dto.response.user.UserDto;
import com.example.zev.entity.Permission;
import com.example.zev.entity.Role;
import com.example.zev.entity.User;
import com.example.zev.exception.BusinessException;
import com.example.zev.mapper.UserMapper;
import com.example.zev.repository.PermissionRepository;
import com.example.zev.repository.RoleRepository;
import com.example.zev.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService extends CrudServiceImplV2<User, UserDto> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserMapper userMapper;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userMapper = userMapper;
    }


    @Override
    @Transactional
    public UserDto create(UserDto user) throws BusinessException {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        User newUser = userMapper.create(user);
        newUser.setPassword(hashPassword);
        Role role = roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
        Set<Long> permissions = user.getRole().getPermissions()
                .stream().map(Permission::getId)
                .collect(Collectors.toSet());

        role.setPermissions(new HashSet<>(permissionRepository.findAllById(permissions)));
        roleRepository.save(role);
        newUser.setRole(role);

        newUser = userRepository.save(newUser);

        return userMapper.toDto(newUser);
    }

}
