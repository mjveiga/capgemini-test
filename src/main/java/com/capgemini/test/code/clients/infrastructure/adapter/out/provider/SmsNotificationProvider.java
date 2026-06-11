package com.capgemini.test.code.clients.infrastructure.adapter.out.provider;

import com.capgemini.test.code.clients.application.port.out.SmsNotificationPort;
import com.capgemini.test.code.clients.infrastructure.adapter.out.provider.request.SmsNotificationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SmsNotificationProvider implements SmsNotificationPort {
  private final SmsClient smsClient;

  @Override
  public void sendPhoneNotificacion(String phone) {
    String message = "usuario guardado";
    SmsNotificationRequest request = new SmsNotificationRequest(phone, message);
    smsClient.sendNotification(request);
  }
}
