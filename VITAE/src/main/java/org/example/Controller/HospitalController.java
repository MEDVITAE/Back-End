package org.example.Controller;

import jakarta.transaction.Transactional;
import org.example.Domain.Hospital;
import org.example.Records.Hospital.AtualizarHospital;
import org.example.Records.Hospital.RecordHospital;
import org.example.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    @Autowired
    private HospitalService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Hospital> cadastrar(@RequestBody RecordHospital dados){
        return ResponseEntity.status(201).body(service.save(new Hospital(dados)));
    }

    @GetMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN')")
    public ResponseEntity<List<Hospital>> listar(){
        List<Hospital> listHospital = service.findAll();

        if (listHospital.isEmpty()){
            return ResponseEntity.status(204).body(listHospital);
        }
        return ResponseEntity.status(200).body(listHospital);
    }

    @PutMapping
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> atualizar(@RequestBody AtualizarHospital dados){

        Hospital hospitalAtualizado = service.updateHospital(dados);

        if (hospitalAtualizado == null) {
            ResponseEntity.status(404).body("Não foi possivel atualizar");
        }

        return ResponseEntity.status(200).body("hospital atualizado: " + dados.nome());
    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> DeletaUser(@PathVariable long id){

        String result = service.delete(id);

        if (result == null){
            ResponseEntity.badRequest().body("Hospital not found!");
        }

        return ResponseEntity.ok().body("Hospital deletado, id:" + result);
    }
}
