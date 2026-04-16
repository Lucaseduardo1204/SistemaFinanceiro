package com.sistemadegestaofinanceira.controller;

import com.sistemadegestaofinanceira.dtos.LoginRequestDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Lógica: a gente pega o DTO, transforma em um "token" nativo do Spring e manda o AuthenticationManager tentar validar.
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;

    public AuthController(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody @Valid LoginRequestDTO dados){

        // PAsso 1: formatar o email e senha do dto em um formato que o SpringSecurity entenda
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());

        //Passo 2: puxar do banco de dados e tentar validar. Caso der errado, retorna 403
        var authentication = authenticationManager.authenticate(authenticationToken);

        //Se ambas validações forem concluidas. entao:
        return ResponseEntity.ok("Login realizado com sucesso");


    }

}
