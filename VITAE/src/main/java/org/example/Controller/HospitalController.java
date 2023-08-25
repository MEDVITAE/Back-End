package org.example.Controller;

import org.example.Domain.Hospital;
import org.example.Records.Hospital.AtualizarHospital;
import org.example.Records.Hospital.RecordHospital;
import org.example.interfaces.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {
    @Autowired
    private HospitalRepository repository;
    @GetMapping
    public ResponseEntity<List<Hospital>> listar(){
        return ResponseEntity.status(200).body(repository.findAll());
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody RecordHospital dados){
        return ResponseEntity.status(201).body(repository.save(new Hospital(dados)));
    }

    @PutMapping("/{id}")
    public ResponseEntity atualizar(@PathVariable Long id , @RequestBody AtualizarHospital dados){
        if (repository.existsById(id)){
            var selectHospital = repository.getReferenceById(id);
            selectHospital.AtualizaHospital(dados);
            return ResponseEntity.status(200).body(new AtualizarHospital(selectHospital));
        }
        return ResponseEntity.status(404).build();
    }
}
