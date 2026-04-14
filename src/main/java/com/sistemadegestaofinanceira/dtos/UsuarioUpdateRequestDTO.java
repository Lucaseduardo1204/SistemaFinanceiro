package com.sistemadegestaofinanceira.dtos;

import com.sistemadegestaofinanceira.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UsuarioUpdateRequestDTO(
        @NotBlank(message = "O usuário não pode estar em branco")
        String nomeUsuario,

        @NotBlank(message = "O email é obrigatório")
        @Email(message = "O formato de email deve ser válido")
        String email,

        @NotNull(message = "O tipo de usuário é obrigatório")
        TipoUsuario tipoUsuario

) {
}
