package com.sistemadegestaofinanceira.controller;

import com.sistemadegestaofinanceira.dtos.UsuarioRequestDTO;
import com.sistemadegestaofinanceira.dtos.UsuarioResponseDTO;
import com.sistemadegestaofinanceira.dtos.UsuarioUpdateRequestDTO;
import com.sistemadegestaofinanceira.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> cadastrar(
            @Valid @RequestBody UsuarioRequestDTO request
            ){

        UsuarioResponseDTO response = service.cadastrarUsuario(request);

        return  ResponseEntity.status(201).body(response);
    }

//    @PathVariable é uma anotação do Spring utilizada para extrair valores diretamente do caminho (path)
//    da URI de uma requisição HTTP e vinculá-los a parâmetros de um método controlador.

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id){
        UsuarioResponseDTO response = service.buscarPorId(id);

        return ResponseEntity.ok(response);

    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponseDTO>> listarTodos() {
        List<UsuarioResponseDTO> response = service.listarTodos();

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> atualizar(@PathVariable Long id, @Valid @RequestBody UsuarioUpdateRequestDTO request){
        UsuarioResponseDTO response = service.atualizarUsuario(id, request);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id){

        // Manda o Service apagar
        service.deletar(id);

        // Retorna o 204, no content
        return ResponseEntity.noContent().build();

    }

}