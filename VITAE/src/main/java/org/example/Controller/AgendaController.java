package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.Domain.Agenda;
import org.example.Domain.Hospital;
import org.example.Records.Agenda.AtualizaAgenda;
import org.example.Records.Agenda.RecordAgenda;
import org.example.Records.Doacao.RecordDoacao;
import org.example.Records.Endereco.AtualizaEndereco;
import org.example.Records.Hospital.AtualizarHospital;
import org.example.Records.Hospital.RecordHospital;
import org.example.interfaces.AgendaRepository;
import org.example.interfaces.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Agenda")

public class AgendaController {
    @Autowired
    private AgendaRepository repository;
    @GetMapping
    public ResponseEntity<List<Agenda>> listar(){
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody RecordAgenda dados){
        System.out.println( dados);
        return ResponseEntity.status(201).body(repository.save(new Agenda(dados)));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity atualizar(@PathVariable Long id , @RequestBody AtualizaAgenda dados){
        if (repository.existsById(id)){
            var agenda = repository.getReferenceById(id);
            agenda.atualizaAgenda(dados);
            return ResponseEntity.status(200).body((new AtualizaAgenda(agenda)));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public  ResponseEntity DeletaUser(@PathVariable long id){
        repository.deleteById(id);

        return ResponseEntity.badRequest().build();
    }
}
