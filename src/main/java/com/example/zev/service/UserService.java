package com.example.zev.service;

import com.example.zev.constants.ErrorCode;
import com.example.zev.entity.Permission;
import com.example.zev.entity.Role;
import com.example.zev.entity.User;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.PermissionRepository;
import com.example.zev.repository.RoleRepository;
import com.example.zev.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService extends CrudServiceImpl<User> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository, RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }


    @Override
    public User create(User user) throws BusinessException {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        Role role = roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
        Set<Long> permissions = user.getRole().getPermissions()
                .stream().map(Permission::getId)
                .collect(Collectors.toSet());

        role.setPermissions(new HashSet<>(permissionRepository.findAllById(permissions)));
        role = roleRepository.save(role);
        user.setRole(role);
        user =  userRepository.save(user);
        return user;
    }
}
