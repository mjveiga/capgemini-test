package com.capgemini.test.code.clients.application.port.in;

import com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto.IdUserResponseDto;
import com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto.UserResponseDto;

public interface UserManagementUseCase {
  IdUserResponseDto createHandle(String name, String email, String phone, String rol, String dni);
  UserResponseDto getHandle(Integer id);
}
