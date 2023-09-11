package org.example.Controller;

import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import org.example.Domain.Hospital;
import org.example.Records.Hospital.AtualizarHospital;
import org.example.Records.Hospital.RecordHospital;
import org.example.interfaces.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalRepository repository;
    @GetMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN')")
    public ResponseEntity<List<Hospital>> listar(){

        return ResponseEntity.status(200).body(repository.findAll());
    }

    @PostMapping
    @Transactional

    public ResponseEntity cadastrar(@RequestBody RecordHospital dados){
        System.out.println(dados);
        return ResponseEntity.status(201).body(repository.save(new Hospital(dados)));
    }

    @PutMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity atualizar(@PathVariable Long id , @RequestBody AtualizarHospital dados){
        if (repository.existsById(id)){

            var selectHospital = repository.getReferenceById(id);
            selectHospital.AtualizaHospital(dados);
            return ResponseEntity.status(200).body(new AtualizarHospital(selectHospital));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity DeletaUser(@PathVariable long id){

        repository.deleteById(id);

        return ResponseEntity.badRequest().build();
    }
}
