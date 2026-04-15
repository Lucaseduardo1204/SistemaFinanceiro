package com.sistemadegestaofinanceira.controller;

import com.sistemadegestaofinanceira.dtos.LancamentoRequestDTO;
import com.sistemadegestaofinanceira.dtos.LancamentoResponseDTO;
import com.sistemadegestaofinanceira.entities.Lancamento;
import com.sistemadegestaofinanceira.service.LancamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/despesas")
public class LancamentoController {

    private final LancamentoService lancamentoService;

    public LancamentoController(LancamentoService lancamentoService){
        this.lancamentoService = lancamentoService;
    }

    @PostMapping
    public ResponseEntity<LancamentoResponseDTO> novaDespesa(@Valid @RequestBody LancamentoRequestDTO request){

        LancamentoResponseDTO response = lancamentoService.novaDespesa(request);

        return ResponseEntity.status(201).body(response);

    }

    //construir listagem única
    @GetMapping("/{id}")
    public ResponseEntity<LancamentoResponseDTO> buscaLancamentoPorId(@PathVariable Long id){

        LancamentoResponseDTO response = lancamentoService.buscaLancamentoPorId(id);

        return ResponseEntity.ok().body(response);
    }


    @GetMapping
    public ResponseEntity<List<LancamentoResponseDTO>> listarLancamentos(){
        List<LancamentoResponseDTO> response = lancamentoService.listarLancamentos();

        return ResponseEntity.ok().body(response);
    }




}
