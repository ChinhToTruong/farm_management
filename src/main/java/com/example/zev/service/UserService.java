package com.example.zev.service;

import com.example.zev.constants.ErrorCode;
import com.example.zev.entity.Permission;
import com.example.zev.entity.Role;
import com.example.zev.entity.User;
import com.example.zev.exception.BusinessException;
import com.example.zev.repository.PermissionRepository;
import com.example.zev.repository.RoleRepository;
import com.example.zev.repository.SearchRepository;
import com.example.zev.repository.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService extends CrudServiceImpl<User> {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;


    @Override
    public User create(User user) throws BusinessException {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        user =  userRepository.save(user);
        Role role = roleRepository.findById(user.getRole().getId())
                .orElseThrow(() -> new BusinessException(ErrorCode.ENTITY_NOT_FOUND));
        Set<Permission> permissions = user.getRole().getPermissions()
                .stream().map(permissionRepository::save)
                .collect(Collectors.toSet());
        role.setPermissions(permissions);
        user.setRole(role);
        return user;
    }
}
