package com.sistemadegestaofinanceira.dtos;

import com.sistemadegestaofinanceira.enums.TipoUsuario;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


//O pacote persistence (JPA/Hibernate) só deve existir na camada de Entity

public record UsuarioRequestDTO (

        @NotBlank(message = "O nome não pode estar em branco")
        String nomeUsuario,

        @NotBlank(message = "Email obrigatório!")
        @Email(message = "Formato de email inválido!")
        String email,

        @NotBlank(message = "A senha é obrigatória")
        String senha,

        @NotNull(message = "O tipo de usuário é obrigatório")
        TipoUsuario tipoUsuario){
}
