package com.sistemadegestaofinanceira.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  //Permite com que de pra manipular
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return  http
                //Desliga o csrf (proteção para formulários web antigos, não utilizado em API REST)
                .csrf(csrf -> csrf.disable())
                //Indica que a API não guardará a sessão (cookies). Adiante será visto o JWT
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                //Autorização de quem pode acessar a rota e quem não pode
                .authorizeHttpRequests(authorize -> authorize
                        //Libera todos apenas para criar um usuário novo
                        .requestMatchers(HttpMethod.POST, "/api/usuarios").permitAll()
                        //Qualquer outra rota do sistema dependerá da autenticação
                        .anyRequest().authenticated()
                )
                .build();

    }

    //Método que indica ao Spring qual a maquina oficial de criptografia do sistema
    @Bean
    public org.springframework.security.crypto.password.PasswordEncoder passwordEncoder(){
        return new org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder();
    }

    // Método que confere a senha presente no DTO e compara com o hash presente no banco
    @Bean
    public org.springframework.security.authentication.AuthenticationManager authenticationManager(
            org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration configuration
    )throws Exception {
        return configuration.getAuthenticationManager();
    }

}
