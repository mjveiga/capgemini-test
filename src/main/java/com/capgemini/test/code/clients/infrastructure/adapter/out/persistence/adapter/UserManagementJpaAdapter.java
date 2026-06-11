package com.capgemini.test.code.clients.infrastructure.adapter.out.persistence.adapter;

import com.capgemini.test.code.clients.application.port.out.UserManagementPort;
import com.capgemini.test.code.clients.domain.model.User;
import com.capgemini.test.code.clients.infrastructure.adapter.exception.ServiceErrorCodes;
import com.capgemini.test.code.clients.infrastructure.adapter.exception.ServiceException;
import com.capgemini.test.code.clients.infrastructure.adapter.out.persistence.jpa.entity.UserEntity;
import com.capgemini.test.code.clients.infrastructure.adapter.out.persistence.jpa.entity.RoomEntity;
import com.capgemini.test.code.clients.infrastructure.adapter.out.persistence.jpa.repository.UserManagementRepository;
import com.capgemini.test.code.clients.infrastructure.adapter.out.persistence.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserManagementJpaAdapter implements UserManagementPort {
  private final UserManagementRepository repository;
  private final UserMapper mapper;

  @Override
  public User createUser(String name, String email, String phone, String rol, String dni) {
    if (repository.findByEmail(email).isPresent()) {
      throw new ServiceException("error validation email", ServiceErrorCodes.RESOURCE_ALREADY_EXISTS);
    }

    UserEntity newUser = UserEntity.builder()
        .name(name)
        .email(email)
        .phone(phone)
        .rol(rol)
        .dni(dni)
        .room(RoomEntity.builder().id(1).build())
        .build();

    UserEntity savedUser = repository.save(newUser);
    return mapper.toDomain(savedUser);
  }

  @Override
  public User getUser(Integer id) {
    return repository.findByIdAndRoom(id)
        .map(mapper::toDomain)
        .orElseThrow(() -> new ServiceException("User not found", ServiceErrorCodes.RESOURCE_NOT_FOUND));
  }
}
