package com.sistemadegestaofinanceira.entities;

import com.sistemadegestaofinanceira.enums.Categoria;
import com.sistemadegestaofinanceira.enums.TipoOperacao;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "tb_lancamento")
@Getter
@Setter
public class Lancamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDateTime data;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private BigDecimal valor;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoOperacao tipoOperacao;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Categoria categoria;


    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    protected Lancamento(){};

    public Lancamento(String descricao, BigDecimal valor, TipoOperacao tipoOperacao, Categoria categoria, Usuario usuario){
        this.data = LocalDateTime.now();
        this.descricao = descricao;
        this.valor = valor;
        this.tipoOperacao = tipoOperacao;
        this.categoria = categoria;
        this.usuario = usuario;
    }



}
