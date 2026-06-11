package com.capgemini.test.code.clients.domain.model;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class User {
  Integer id;
  String name;
  String email;
  String phone;
  String rol;
  String dni;
  Room room;
}
