package com.example.zev.mapper;

import com.example.zev.dto.response.user.UserDto;
import com.example.zev.entity.User;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(config =  BaseMapperConfig.class, uses = {PermissionMapper.class, RoleMapper.class})
public interface UserMapper extends BaseMapper<User, UserDto>{

}
