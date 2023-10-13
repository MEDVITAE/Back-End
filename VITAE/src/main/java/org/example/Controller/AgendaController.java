package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.DTO.AgendamentoDTO;
import org.example.Domain.Agenda;
import org.example.Records.Agenda.AtualizaAgenda;
import org.example.Records.Agenda.RecordAgenda;
import org.example.Service.ArquivoCsvService;
import org.example.interfaces.AgendaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Agenda")

public class AgendaController {
    @Autowired
    private AgendaRepository repository;

    @Autowired
    private ArquivoCsvService serviceRepository;

    @GetMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') ")
    public ResponseEntity<List<Agenda>> listar() {
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @PostMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE')")
    public ResponseEntity cadastrar(@RequestBody RecordAgenda dados) {
        System.out.println(dados);
        return ResponseEntity.status(201).body(repository.save(new Agenda(dados)));
    }

    @PutMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE')")
    public ResponseEntity atualizar(@PathVariable Long id, @RequestBody AtualizaAgenda dados) {
        if (repository.existsById(id)) {
            var agenda = repository.getReferenceById(id);
            agenda.atualizaAgenda(dados);
            return ResponseEntity.status(200).body((new AtualizaAgenda(agenda)));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public ResponseEntity DeletaUser(@PathVariable long id) {
        repository.deleteById(id);

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/gerarArquivoCsv")
    public ResponseEntity<Void> getUsuariosEAgendas() {
        serviceRepository.recupararValores();

        return ResponseEntity.status(200).build();
    }
}
