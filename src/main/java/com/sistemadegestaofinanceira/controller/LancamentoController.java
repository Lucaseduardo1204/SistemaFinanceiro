package com.sistemadegestaofinanceira.controller;

import com.sistemadegestaofinanceira.dtos.LancamentoRequestDTO;
import com.sistemadegestaofinanceira.dtos.LancamentoResponseDTO;
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

    @GetMapping
    public ResponseEntity<List<LancamentoResponseDTO>> listarDespesas(){
        List<LancamentoResponseDTO> response = lancamentoService.listarDespesas();

        return ResponseEntity.ok().body(response);
    }




}
