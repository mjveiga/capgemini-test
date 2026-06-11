package com.capgemini.test.code.clients.infrastructure.adapter.out.provider;

import com.capgemini.test.code.clients.infrastructure.adapter.out.provider.request.CheckDniRequest;
import com.capgemini.test.code.clients.infrastructure.adapter.out.provider.response.CheckDniResponse;
import com.capgemini.test.code.clients.application.port.out.DniValidatorPort;
import com.capgemini.test.code.clients.infrastructure.adapter.exception.ServiceErrorCodes;
import com.capgemini.test.code.clients.infrastructure.adapter.exception.ServiceException;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DniValidationProvider implements DniValidatorPort {

  private final DniClient dniClient;

  @Override
  public CheckDniResponse validateDni(String dni) {
    try {
      CheckDniRequest checkDniRequest = new CheckDniRequest(dni);
      ResponseEntity<CheckDniResponse> response = dniClient.check(checkDniRequest);
      return response.getBody();
    } catch (FeignException.Conflict e) {
      throw new ServiceException(ServiceErrorCodes.DNI_CONFLICT);
    }
  }
}

