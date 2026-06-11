package com.capgemini.test.code.clients.infrastructure.adapter.out.provider;

import com.capgemini.test.code.clients.application.port.out.EmailNotificationPort;
import com.capgemini.test.code.clients.infrastructure.adapter.out.provider.request.EmailNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmailNotificationProvider implements EmailNotificationPort {

  private final EmailClient emailClient;

  @Override
  public void sendEmailNotificacion(String email) {
    String message = "usuario guardado";
    EmailNotificationRequest request = new EmailNotificationRequest(email, message);
    emailClient.sendNotification(request);
  }
}

