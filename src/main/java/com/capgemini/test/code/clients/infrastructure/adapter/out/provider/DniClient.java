package com.capgemini.test.code.clients.infrastructure.adapter.out.provider;

import com.capgemini.test.code.clients.infrastructure.adapter.out.provider.request.CheckDniRequest;
import com.capgemini.test.code.clients.infrastructure.adapter.out.provider.response.CheckDniResponse;
import feign.Logger;
import feign.okhttp.OkHttpClient;
import okhttp3.OkHttpClient.Builder;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "checkDniClient", url = "${external.service.url}", configuration = DniClient.FeignConfig.class)
public interface DniClient {
  @Configuration
  public class FeignConfig {

    @Bean
    public OkHttpClient client() {
      return new OkHttpClient(new Builder().build());
    }

    @Bean
    public Logger.Level feignLoggerLevel() {
      return Logger.Level.FULL;
    }
  }

  @PatchMapping(value = "/check-dni")
  ResponseEntity<CheckDniResponse> check(@RequestBody CheckDniRequest request);
}
 
