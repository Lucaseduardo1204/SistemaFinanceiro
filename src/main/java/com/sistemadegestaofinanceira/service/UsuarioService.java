package com.sistemadegestaofinanceira.service;

import com.sistemadegestaofinanceira.dtos.UsuarioRequestDTO;
import com.sistemadegestaofinanceira.dtos.UsuarioResponseDTO;
import com.sistemadegestaofinanceira.dtos.UsuarioUpdateRequestDTO;
import com.sistemadegestaofinanceira.entities.Usuario;
import com.sistemadegestaofinanceira.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository repository;


    public UsuarioService(UsuarioRepository repository){
        this.repository = repository;
    }

    @Transactional
    public UsuarioResponseDTO cadastrarUsuario(UsuarioRequestDTO request){
        var userExistente = repository.findByNomeUsuario(request.nomeUsuario());
        var emailExistente = repository.findByEmail(request.email());

        if (userExistente.isPresent()){
            throw new RuntimeException("ERRO: Usuário já cadastrado!");
        }

        if (emailExistente.isPresent()){
            throw new RuntimeException("ERRO: Email já cadastrado!");
        }

        Usuario novoUsuario = new Usuario(request.nomeUsuario(), request.email(), request.senha(), request.tipoUsuario());

        repository.save(novoUsuario);

        return new UsuarioResponseDTO(novoUsuario.getId(), novoUsuario.getNomeUsuario(), novoUsuario.getEmail(), novoUsuario.getTipoUsuario());

    }

    //Dúvida: Pro Get é necessário o @Transactional?
    @Transactional
    public UsuarioResponseDTO buscarPorId(Long id){
        //Busca no banco
        var usuarioBox = repository.findById(id);

        //Esse usuário existe? PAra isso temos que construir a verificação
        if (usuarioBox.isEmpty()){ //Se estiver vazio, lança a exceção
            throw new RuntimeException("Usuário não encontrado!");
        }

        //precisamos tirar ele da caixa agora e converte-lo no DTO, então...
        Usuario usuario = usuarioBox.get();
        return new UsuarioResponseDTO(usuario.getId(), usuario.getNomeUsuario(), usuario.getEmail(), usuario.getTipoUsuario());

    }

    //Listar todos
    public List<UsuarioResponseDTO> listarTodos(){
        //Pra pegar todos os usuários do banco:
        List<Usuario> listaDeEntidades = repository.findAll();

        //Stream API
        //Lógica: Pega a Lista -> coloca na esteira(stream) -> transforma (map) cada u em um DTO -> junta tudo numa lista nova (toList)
        return listaDeEntidades.stream()
                .map(u -> new UsuarioResponseDTO(u.getId(), u.getNomeUsuario(), u.getEmail(), u.getTipoUsuario())).toList();

    }

    @Transactional
    public  UsuarioResponseDTO atualizarUsuario(Long id, UsuarioUpdateRequestDTO request){
        //Busca o usuário pelo id, em caso de não encontrar ja lança a exceção
        //.orElseThrow() faz a função do if e .get na mesma linha
        Usuario usuario = repository.findById(id).orElseThrow(() -> new RuntimeException("ERRO: Usuário não encontrado!"));

        //Caso o email exista mas pertença a outra pessoa será outra exceção
        var emailExistente = repository.findByEmail(request.email());
        if (emailExistente.isPresent() && !emailExistente.get().getId().equals(id)){
            throw new RuntimeException("Esse email já está em uso por outro usuário!");

        }

        //Verifica se o nome de usuário pertence a outra pessoa
        var userExistente = repository.findByNomeUsuario(request.nomeUsuario());
        if (userExistente.isPresent() && !userExistente.get().getId().equals(request.email())){
            throw new RuntimeException("Esse nome de usuário já pertence a outra pessoa!");
        }

        //Caso não caia em nenhuma das exceções agora precisamos atualizar o banco da entidade utilizando os setters
        usuario.setNomeUsuario(request.nomeUsuario());
        usuario.setEmail(request.email());
        usuario.setTipoUsuario(request.tipoUsuario());

        repository.save(usuario);

        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNomeUsuario(),
                usuario.getEmail(),
                usuario.getTipoUsuario()
        );

    }

    @Transactional
    public void deletar(Long id){

        if (!repository.existsById(id)){
            throw new RuntimeException("ERRO: Usuário não encontrado!");
        }

        repository.deleteById(id);

    }

}
