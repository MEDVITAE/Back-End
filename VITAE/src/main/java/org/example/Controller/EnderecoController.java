package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.Domain.Endereco;
import org.example.Records.Endereco.AtualizaEndereco;
import org.example.Records.Endereco.RecordEndereco;
import org.example.Records.Usuario.AtualizarUser;
import org.example.interfaces.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Endereco")
public class EnderecoController {
    @Autowired
    private EnderecoRepository repository;
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    @GetMapping
    public ResponseEntity<List<Endereco>> listar() {
        return ResponseEntity.status(200).body(repository.findAll());
    }
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    @PostMapping
    public ResponseEntity cadastar(@RequestBody RecordEndereco dados) {
        return ResponseEntity.status(200).body(repository.save(new Endereco(dados)));
    }

    @PutMapping("{id}")
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    @Transactional
    public ResponseEntity AtualizaEndereco(@PathVariable Long id,@RequestBody AtualizaEndereco dados) {
        var endereco = repository.getReferenceById(id);
        endereco.AtualizaEndereco(dados);
        return ResponseEntity.status(200).body((new AtualizaEndereco(endereco)));
    }
    @Transactional
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    @DeleteMapping("{id}")
    public  ResponseEntity DeletaUser(@PathVariable long id){
        repository.deleteById(id);
        return ResponseEntity.status(204).build();
    }



}
