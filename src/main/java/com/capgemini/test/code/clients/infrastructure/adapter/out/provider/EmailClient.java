package com.capgemini.test.code.clients.infrastructure.adapter.out.provider;

import com.capgemini.test.code.clients.infrastructure.adapter.out.provider.request.EmailNotificationRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "emailClient", url = "${external.service.url}")
public interface EmailClient {

  @PostMapping(value = "/email")
  ResponseEntity<Void> sendNotification(@RequestBody EmailNotificationRequest request);
}

