package com.sistemadegestaofinanceira.repository;

import com.sistemadegestaofinanceira.entities.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    Optional<Lancamento> findById(Long id);
    List<Lancamento> findByUsuarioId(Long id);

}
