package com.capgemini.test.code.clients.infrastructure.adapter.out.provider;

import com.capgemini.test.code.clients.infrastructure.adapter.out.provider.request.SmsNotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "smsClient", url= "${external.service.url}")
public interface SmsClient {
  @PostMapping(value= "/sms")
   ResponseEntity<Void> sendNotification(@RequestBody SmsNotificationRequest request);
}
