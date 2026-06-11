package com.capgemini.test.code.clients.application.port.out;

import com.capgemini.test.code.clients.domain.model.User;

public interface UserManagementPort {
  User createUser(String name, String email, String phone, String rol, String dni);
  User getUser(Integer id);
}
