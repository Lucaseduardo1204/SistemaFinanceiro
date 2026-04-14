package com.sistemadegestaofinanceira.dtos;

import com.sistemadegestaofinanceira.enums.TipoUsuario;

public record UsuarioResponseDTO(Long id, String nomeUsuario, String email, TipoUsuario tipoUsuario) {
}
