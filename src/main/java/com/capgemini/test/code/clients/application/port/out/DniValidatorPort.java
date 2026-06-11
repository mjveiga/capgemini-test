package com.capgemini.test.code.clients.application.port.out;

import com.capgemini.test.code.clients.infrastructure.adapter.out.provider.response.CheckDniResponse;

public interface DniValidatorPort {
  CheckDniResponse validateDni(String dni);
}
