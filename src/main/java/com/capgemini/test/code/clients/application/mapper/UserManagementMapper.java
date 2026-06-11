package com.capgemini.test.code.clients.application.mapper;

import com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto.IdUserResponseDto;
import com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto.UserResponseDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserManagementMapper {
  IdUserResponseDto toResponse(Integer id);
  UserResponseDto toUserResponseDto(String name, String email, String phone, String rol, String dni, Integer room);
}
