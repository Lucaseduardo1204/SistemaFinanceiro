package com.sistemadegestaofinanceira.dtos;

import com.sistemadegestaofinanceira.enums.Categoria;
import com.sistemadegestaofinanceira.enums.TipoOperacao;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record LancamentoResponseDTO(
        Long id,
        String descricao,
        BigDecimal valor,
        TipoOperacao tipoOperacao,
        Categoria categoria,
        LocalDateTime data, // A data que o servidor gerou
        String nomeUsuario
) {}