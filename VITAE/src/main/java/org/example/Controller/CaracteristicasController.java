package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.Domain.Caracteristicas;
import org.example.Records.Caracteristicas.AtualizaCaracteristicas;
import org.example.Records.Caracteristicas.RecordCaracteristicas;
import org.example.interfaces.CaractetisticasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Caracteristicas")

public class CaracteristicasController {
    @Autowired
    private CaractetisticasRepository caracteristicaService;

    @GetMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN') ")
    public ResponseEntity<List<Caracteristicas>> listar() {
        return ResponseEntity.status(200).body(caracteristicaService.findAll()); }

    @PostMapping
    @PreAuthorize(" hasRole('PACIENTE') || hasRole('RECEPCAO') ")
    public ResponseEntity cadastrar(@RequestBody RecordCaracteristicas dados) {
        return ResponseEntity.status(201).body(caracteristicaService.save(new Caracteristicas(dados)));
    }

    @PutMapping("{id}")
    @Transactional
    @PreAuthorize(" hasRole('PACIENTE') || hasRole('RECEPCAO') ")
    public ResponseEntity atualizar(@RequestBody AtualizaCaracteristicas dados, @PathVariable Long id) {
            var caracteristicas = caracteristicaService.getReferenceById(id);
            caracteristicas.AtualizaCaracteristicas(dados);
            return ResponseEntity.ok(new AtualizaCaracteristicas(caracteristicas));
    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasRole('PACIENTE') || hasRole('RECEPCAO')")
    public ResponseEntity deletar(@PathVariable Long id) {
        caracteristicaService.deleteById(id);
        return ResponseEntity.status(204).build();
    }
}
