package com.capgemini.test.code.clients.application.usecase.handler;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.capgemini.test.code.clients.application.mapper.UserManagementMapper;
import com.capgemini.test.code.clients.application.port.out.DniValidatorPort;
import com.capgemini.test.code.clients.application.port.out.EmailNotificationPort;
import com.capgemini.test.code.clients.application.port.out.SmsNotificationPort;
import com.capgemini.test.code.clients.application.port.out.UserManagementPort;
import com.capgemini.test.code.clients.domain.model.Room;
import com.capgemini.test.code.clients.domain.model.User;
import com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto.IdUserResponseDto;
import com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto.UserResponseDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserManagementHandlerTest {

  @Mock
  private UserManagementPort userManagementPort;
  @Mock
  private DniValidatorPort dniValidatorPort;
  @Mock
  private EmailNotificationPort emailNotificationPort;
  @Mock
  private SmsNotificationPort smsNotificationPort;
  @Mock
  private UserManagementMapper userManagementMapper;

  @InjectMocks
  private UserManagementHandler userManagementHandler;

  @Test
  void testGetHandle(){
    User user = buildCreateAdminUser();

    UserResponseDto expectedResponse = new UserResponseDto(user.getName(), user.getEmail(), user.getPhone(), user.getRol(), user.getDni(), user.getRoom().getId());

    when(userManagementPort.getUser(any())).thenReturn(user);
    when(userManagementMapper.toUserResponseDto(any(), any(), any(), any(), any(), any())).thenReturn(expectedResponse);

    userManagementHandler.getHandle(user.getId());

    verify(userManagementPort).getUser(any());
  }

  @Test
  void testCreateHandleWithAdminRoleAndEmailNotification() {
    User createdUser = buildCreateAdminUser();

    IdUserResponseDto expectedResponse = new IdUserResponseDto(createdUser.getId());

    when(userManagementPort.createUser(any(), any(), any(), any(), any()))
        .thenReturn(createdUser);
    when(userManagementMapper.toResponse(any()))
        .thenReturn(expectedResponse);

    IdUserResponseDto response = userManagementHandler.createHandle(createdUser.getName(), createdUser.getEmail(), createdUser.getPhone(), createdUser.getRol(), createdUser.getDni());

    assertNotNull(response);
    verify(userManagementPort).createUser(any(), any(), any(), any(), any());
    verify(dniValidatorPort).validateDni(any());
    verify(emailNotificationPort).sendEmailNotificacion(any());
  }

  @Test
  void testCreateHandleWithSuperAdminRoleAndSmsNotification() {
    User createdUser = buildCreateSuperAdminUser();

    IdUserResponseDto expectedResponse = new IdUserResponseDto(createdUser.getId());

    when(userManagementPort.createUser(any(), any(), any(), any(), any()))
        .thenReturn(createdUser);
    when(userManagementMapper.toResponse(any()))
        .thenReturn(expectedResponse);

    IdUserResponseDto response = userManagementHandler.createHandle(createdUser.getName(), createdUser.getEmail(), createdUser.getPhone(), createdUser.getRol(), createdUser.getDni());

    assertNotNull(response);
    verify(userManagementPort).createUser(any(), any(), any(), any(), any());
    verify(dniValidatorPort).validateDni(any());
    verify(smsNotificationPort).sendPhoneNotificacion(any());
  }

  private User buildCreateAdminUser() {
    return User.builder()
        .id(1)
        .name("pablo")
        .email("pablo@email.com")
        .phone("677998899")
        .rol("ADMIN")
        .dni("23454234W")
        .room(Room.builder().id(1).build())
        .build();
  }

  private User buildCreateSuperAdminUser(){
    return User.builder()
        .id(2)
        .name("juan")
        .email("juan@email.com")
        .phone("677998899")
        .rol("SUPERADMIN")
        .dni("23454234W")
        .build();
  }
}


