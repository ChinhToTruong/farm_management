package com.example.zev.mapper;

import com.example.zev.dto.response.role.RoleDto;
import com.example.zev.entity.Role;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class, uses = {PermissionMapper.class})
public interface RoleMapper extends BaseMapper<Role, RoleDto>{
}
