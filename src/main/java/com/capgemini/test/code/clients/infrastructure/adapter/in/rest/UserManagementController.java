package com.capgemini.test.code.clients.infrastructure.adapter.in.rest;

import com.capgemini.test.code.clients.application.port.in.UserManagementUseCase;
import com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto.UserCreateRequestDto;
import com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto.IdUserResponseDto;
import com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto.UserResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserManagementController {

  private final UserManagementUseCase userManagementUseCase;

  @PostMapping(value= "/create", produces= MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<IdUserResponseDto> createUser(@RequestBody @Valid UserCreateRequestDto request){
    IdUserResponseDto response = userManagementUseCase.createHandle(request.name, request.email, request.phone, request.rol.name(), request.dni);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @GetMapping(value= "/get/{id}", produces= MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserResponseDto> getUser(@PathVariable Integer id){
    UserResponseDto response = userManagementUseCase.getHandle(id);
    return ResponseEntity.ok(response);
  }
}
