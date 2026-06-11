package com.capgemini.test.code.clients.domain.model;

import java.util.List;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class Room {
  Integer id;
  String name;
  List<User>  users;
}
