package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.Domain.Doacao;
import org.example.Records.Doacao.AtualizaDoacao;
import org.example.Records.Doacao.RecordDoacao;
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
    private DoacaoRepository doacaoService;

    @PostMapping
    @Transactional
    @PreAuthorize("hasRole('ENFERMEIRA') ")
    public ResponseEntity cadastrar(@RequestBody RecordDoacao dados){
        return ResponseEntity.status(201).body(doacaoService.save(new Doacao(dados)));
    }

    @GetMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public ResponseEntity<List<Doacao>> listar(){
        return ResponseEntity.status(200).body(doacaoService.findAll());
    }

    @PutMapping("/{id}")
    @Transactional
    @PreAuthorize(" hasRole('ENFERMEIRA')  ")
    public ResponseEntity atualizar(@PathVariable Long id , @RequestBody AtualizaDoacao dados){
        if (doacaoService.existsById(id)){
            var doacao = doacaoService.getReferenceById(id);
            doacao.atualizaDoacao(dados);
            return ResponseEntity.status(200).body((new AtualizaDoacao(doacao)));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize(" hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public  ResponseEntity DeletaDoacao(@PathVariable long id){

        doacaoService.deleteById(id);

        return ResponseEntity.badRequest().body("Hospital not found!");
    }
}
