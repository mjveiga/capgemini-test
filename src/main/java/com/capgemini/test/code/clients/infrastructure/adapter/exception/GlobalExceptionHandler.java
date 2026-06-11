package com.capgemini.test.code.clients.infrastructure.adapter.exception;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ServiceException.class)
  public ResponseEntity<ErrorResponse> handleServiceException(ServiceException ex) {
    HttpStatus status = mapErrorCodeToHttpStatus(ex.getCode());
    int code = status.value();
    String message = ex.getMessage() != null ? ex.getMessage() : ex.getCode().getDescription();
    ErrorResponse error = new ErrorResponse(code, message);
    return ResponseEntity.status(status).body(error);
  }

  private HttpStatus mapErrorCodeToHttpStatus(ServiceErrorCodes code) {
    return switch (code) {
      case RESOURCE_ALREADY_EXISTS -> HttpStatus.CONFLICT;
      case DNI_CONFLICT -> HttpStatus.CONFLICT;
      case RESOURCE_NOT_FOUND -> HttpStatus.NOT_FOUND;
    };
  }
}

