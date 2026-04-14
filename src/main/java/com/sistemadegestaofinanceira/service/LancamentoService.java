package com.sistemadegestaofinanceira.service;

import com.sistemadegestaofinanceira.dtos.LancamentoRequestDTO;
import com.sistemadegestaofinanceira.dtos.LancamentoResponseDTO;
import com.sistemadegestaofinanceira.entities.Lancamento;
import com.sistemadegestaofinanceira.repository.LancamentoRepository;
import com.sistemadegestaofinanceira.repository.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LancamentoService {
    private final UsuarioRepository usuarioRepository;
    private final LancamentoRepository lancamentoRepository;

    public LancamentoService(UsuarioRepository usuarioRepository, LancamentoRepository lancamentoRepository){
        this.usuarioRepository = usuarioRepository;
        this.lancamentoRepository = lancamentoRepository;
    }


    @Transactional
    public LancamentoResponseDTO novaDespesa(LancamentoRequestDTO request){

        //Busca o dono no banco, caso ele não encontre já estoura a exceção
        var dono = usuarioRepository.findById(request.usuarioId())
                .orElseThrow(() -> new  RuntimeException("ERRO: Usuário não encontrado!"));



        //Cria a novaDespesa passando todos os parametros inclusive o dono inteiro conforme definido na entidade despesa
        Lancamento novaLancamento = new Lancamento(request.descricao(), request.valor(), request.tipoOperacao(), request.categoria(), dono);

        //Salva no banco
        lancamentoRepository.save(novaLancamento);

        //Retorna o pacote de resposta com os 7 parametros
        return new LancamentoResponseDTO(novaLancamento.getId(), novaLancamento.getDescricao(), novaLancamento.getValor(), novaLancamento.getTipoOperacao(),
                novaLancamento.getCategoria(), novaLancamento.getData(), dono.getNomeUsuario());

    }

    public  List<LancamentoResponseDTO> listarDespesas(){
        List<Lancamento> listaLancamentos = lancamentoRepository.findAll();

        return listaLancamentos.stream()
                .map(l -> new LancamentoResponseDTO(l.getId(), l.getDescricao(), l.getValor(), l.getTipoOperacao(),
                        l.getCategoria(), l.getData(), l.getUsuario().getNomeUsuario())).toList();

    }



}
