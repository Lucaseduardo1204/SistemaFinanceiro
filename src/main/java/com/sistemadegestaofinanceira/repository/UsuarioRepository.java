package com.sistemadegestaofinanceira.repository;

import com.sistemadegestaofinanceira.entities.Usuario;
import com.sistemadegestaofinanceira.enums.TipoUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{

//    Se a busca é por algo ÚNICO (como Email, CPF, ID), devolvemos Optional<Usuario>.
//
//    Se a busca pode retornar VÁRIAS pessoas (como Tipo de Usuário, Nome, Status), devolvemos uma Lista (List<Usuario>).

    Optional<Usuario> findByNomeUsuario(String nomeUsuario);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findByTipoUsuario(TipoUsuario tipoUsuario);
}
