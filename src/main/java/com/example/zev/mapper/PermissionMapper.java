package com.example.zev.mapper;

import com.example.zev.dto.response.permission.PermissionDto;
import com.example.zev.entity.Permission;
import org.mapstruct.Mapper;

@Mapper(config = BaseMapperConfig.class)
public interface PermissionMapper extends BaseMapper<Permission, PermissionDto>{
}
