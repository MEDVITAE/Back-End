package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.Domain.Agenda;
import org.example.Records.Agenda.AtualizaAgenda;
import org.example.Records.Agenda.RecordAgenda;
import org.example.Service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")

public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') ")
    public ResponseEntity<List<Agenda>> listar(){

        return ResponseEntity.status(200).body(agendaService.listaAgendamentos());
    }

    @PostMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE')")
    public ResponseEntity<?> cadastrar(@RequestBody RecordAgenda dados){
        System.out.println(dados);
        var agendaCadastro = new Agenda(dados);
        return ResponseEntity.status(201).body(agendaService.salvar(agendaCadastro));
    }

    @PutMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE')")
    public ResponseEntity<AtualizaAgenda> atualizar(@PathVariable Long id , @RequestBody AtualizaAgenda dados){
            var agenda = agendaService.buscarPorId(id);
            agenda.atualizaAgenda(dados);

            return ResponseEntity.status(200).body((new AtualizaAgenda(agenda)));
    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public  ResponseEntity<?> DeletaUser(@PathVariable long id){
        agendaService.deletar(id);

        return ResponseEntity.status(204).build();
    }
}
