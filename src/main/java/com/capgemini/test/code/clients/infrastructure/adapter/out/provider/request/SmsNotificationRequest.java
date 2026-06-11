package com.capgemini.test.code.clients.infrastructure.adapter.out.provider.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SmsNotificationRequest {
  private String phone;
  private String message;
}
