package com.capgemini.test.code.clients.application.usecase.handler;

import com.capgemini.test.code.clients.application.mapper.UserManagementMapper;
import com.capgemini.test.code.clients.application.port.in.UserManagementUseCase;
import com.capgemini.test.code.clients.application.port.out.DniValidatorPort;
import com.capgemini.test.code.clients.application.port.out.EmailNotificationPort;
import com.capgemini.test.code.clients.application.port.out.SmsNotificationPort;
import com.capgemini.test.code.clients.application.port.out.UserManagementPort;
import com.capgemini.test.code.clients.domain.model.User;
import com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto.IdUserResponseDto;
import com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto.UserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserManagementHandler implements UserManagementUseCase {

  private final UserManagementPort userManagementPort;
  private final DniValidatorPort dniValidatorPort;
  private final EmailNotificationPort emailNotificationPort;
  private final SmsNotificationPort smsNotificationPort;

  private final UserManagementMapper userManagementMapper;

  @Override
  public IdUserResponseDto createHandle(String name, String email, String phone, String rol, String dni) {
    User user = userManagementPort.createUser(name, email, phone, rol, dni);

    dniValidatorPort.validateDni(user.getDni());
    if (user.getRol().equalsIgnoreCase("Admin")) {
      emailNotificationPort.sendEmailNotificacion(user.getEmail());
    } else {
      smsNotificationPort.sendPhoneNotificacion(user.getPhone());
    }

    return this.userManagementMapper.toResponse(user.getId());
  }

  @Override
  public UserResponseDto getHandle(Integer id) {
    User user = userManagementPort.getUser(id);

    if (user.getRoom() != null) {
      return this.userManagementMapper.toUserResponseDto(user.getName(), user.getEmail(), user.getPhone(), user.getRol(), user.getDni(), 0);
    }
    return this.userManagementMapper.toUserResponseDto(user.getName(), user.getEmail(), user.getPhone(), user.getRol(), user.getDni(), user.getRoom().getId());


//    Integer roomId = null;
//    if (user.getRoom() != null) {
//      roomId = user.getRoom().getId();
//    }
//    return this.userManagementMapper.toUserResponseDto(user.getName(), user.getEmail(), user.getPhone(), user.getRol(), user.getDni(), roomId);
  }
}
