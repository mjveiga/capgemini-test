package com.capgemini.test.code.clients.infrastructure.adapter.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceErrorCodes implements ErrorCode{

  RESOURCE_ALREADY_EXISTS(0 , "User already exists"),
  DNI_CONFLICT(1, "El DNI proporcionado no es válido"),
  RESOURCE_NOT_FOUND(2, "User not found");

  private final int code;
  private final String description;

  @Override
  public int getCode() {
    return this.code;
  }

  @Override
  public String getDescription() {
    return this.description;
  }
}
