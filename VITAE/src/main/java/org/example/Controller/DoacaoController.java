package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.DTO.DoacaoRankingDTO;
import org.example.DTO.RecuperaPosicaoDTO;
import org.example.Domain.Agenda;
import org.example.Domain.Doacao;
import org.example.Records.Agenda.AtualizaAgenda;
import org.example.Records.Doacao.AtualizaDoacao;
import org.example.Records.Doacao.RecordDoacao;
import org.example.interfaces.AgendaRepository;
import org.example.interfaces.DoacaoRepository;
import org.example.interfaces.RecuperaPosicao;
import org.example.interfaces.RecuperaValoresDoacao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
@RestController
@CrossOrigin(origins = "${allowed.origins}", allowedHeaders = "*")
@RequestMapping("/Doacao")

public class DoacaoController {
    @Autowired
    private DoacaoRepository repository;
    @GetMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public ResponseEntity<List<Doacao>> listar(){
        return ResponseEntity.status(200).body(repository.findAll());
    }
    @GetMapping("/Rank")
    public ResponseEntity<List<DoacaoRankingDTO>> listaTop10(){
        List<RecuperaValoresDoacao> doacao = repository.findAllTop10();
        List<DoacaoRankingDTO> doadores = new ArrayList<>();
        for(RecuperaValoresDoacao recupera : doacao){
            DoacaoRankingDTO doador = new DoacaoRankingDTO(recupera.getNome(), recupera.getQuantidade());
            doadores.add(doador);
        }

        return ResponseEntity.status(200).body(doadores);
    }
    @GetMapping("/Posicao/{id}")
    public ResponseEntity<RecuperaPosicaoDTO> rank(@PathVariable int id){
        RecuperaPosicao posicao = repository.posicao(id);
        RecuperaPosicaoDTO rank = new RecuperaPosicaoDTO(posicao.getPosicao());
        return ResponseEntity.status(200).body(rank);
    }

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ENFERMEIRA') ")
    public ResponseEntity cadastrar(@RequestBody RecordDoacao dados){
        return ResponseEntity.status(201).body(repository.save(new Doacao(dados)));
    }

    @PutMapping("/{id}")
    @Transactional
    @PreAuthorize(" hasRole('ENFERMEIRA')  ")
    public ResponseEntity atualizar(@PathVariable Long id , @RequestBody AtualizaDoacao dados){
        if (repository.existsById(id)){
            var doacao = repository.getReferenceById(id);
            doacao.atualizaDoacao(dados);
            return ResponseEntity.status(200).body((new AtualizaDoacao(doacao)));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize(" hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public  ResponseEntity DeletaDoacao(@PathVariable long id){

        repository.deleteById(id);

        return ResponseEntity.badRequest().build();
    }
}
