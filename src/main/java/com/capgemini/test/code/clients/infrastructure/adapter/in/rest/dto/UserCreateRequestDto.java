package com.capgemini.test.code.clients.infrastructure.adapter.in.rest.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateRequestDto {
  @NotBlank
  @Size(max = 6, message = "El nombre no puede contener más de 6 caracteres")
  public String name = "";
  @NotBlank
  @Pattern(
      regexp = "^[^@]+@[^@]+\\.[^@]+$",
      message = "El email debe contener un @ y un ."
  )
  public String email;
  @NotNull(message = "El teléfono es obligatorio")
  public String phone;
  @NotNull(message = "El rol es obligatorio")
  public Rol rol;
  @NotNull(message = "El dni es obligatorio")
  public String dni;
}

