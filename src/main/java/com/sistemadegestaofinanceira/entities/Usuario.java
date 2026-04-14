package com.sistemadegestaofinanceira.entities;

import com.sistemadegestaofinanceira.enums.TipoUsuario;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "tb_usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nomeUsuario;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Column
    @Enumerated(EnumType.STRING)
    private TipoUsuario tipoUsuario;


    //O Hibernate exige esse construtor vazio por baixo dos panos. Porém se colocarmos a anotação
    // @NoArgsConstructor(access = AccessLevel.PROTECTED) no topo da classe, o Lombok escreve isso invisivelmente
    protected Usuario(){}

    public Usuario(String nomeUsuario, String email, String senha, TipoUsuario tipoUsuario){
        this.nomeUsuario = nomeUsuario;
        this.email = email;
        this.senha = senha;
        this.tipoUsuario = tipoUsuario;
    }

}
