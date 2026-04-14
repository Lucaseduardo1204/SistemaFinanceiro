package com.sistemadegestaofinanceira.dtos;

import com.sistemadegestaofinanceira.enums.Categoria;
import com.sistemadegestaofinanceira.enums.TipoOperacao;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record LancamentoRequestDTO(
        @NotBlank(message = "Descrição não pode estar em branco")
        String descricao,

        @NotNull(message = "O valor não pode ser nulo")
        @Positive(message = "O Valor deve ser positivo")
        BigDecimal valor,

        @NotNull(message = "Preencha o Tipo da Operação")
        TipoOperacao tipoOperacao,

        @NotNull(message = "Categoria não pode ser nula")
        Categoria categoria,

        @NotNull
        Long usuarioId

        ) {

}
