package com.sistemadegestaofinanceira.repository;

import com.sistemadegestaofinanceira.entities.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
        List<Lancamento> findByUsuarioId(Long id);
}
