package com.capgemini.test.code.clients.infrastructure.adapter.exception;

import java.io.Serial;
import lombok.Getter;

@Getter
public class ServiceException extends RuntimeException {
  @Serial
  private static final long serialVersionUID = 1L;
  private final ServiceErrorCodes code;

  public ServiceException(ServiceErrorCodes code) {
    this.code = code;
  }

  public ServiceException(String message, ServiceErrorCodes code) {
    super(message);
    this.code = code;
  }

}
