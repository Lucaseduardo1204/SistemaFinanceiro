package com.sistemadegestaofinanceira.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequestDTO (
        @NotBlank(message = "O email não pode estar vazio")
        @Email(message = "O email deve ser válido")
        String email,

        @NotBlank(message = "A senha não pode estar vazia")
        String senha

) {
}
