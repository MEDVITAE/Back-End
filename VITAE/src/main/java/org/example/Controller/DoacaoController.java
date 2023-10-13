package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.Domain.Agenda;
import org.example.Domain.Doacao;
import org.example.Records.Agenda.AtualizaAgenda;
import org.example.Records.Doacao.AtualizaDoacao;
import org.example.Records.Doacao.RecordDoacao;
import org.example.interfaces.AgendaRepository;
import org.example.interfaces.DoacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Doacao")

public class DoacaoController {
    @Autowired
    private DoacaoRepository repository;
    @GetMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public ResponseEntity<List<Doacao>> listar(){
        return ResponseEntity.status(200).body(repository.findAll());
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
