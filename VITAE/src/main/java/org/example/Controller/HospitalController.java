package org.example.Controller;

import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import org.example.Domain.Hospital;
import org.example.Records.Hospital.AtualizarHospital;
import org.example.Records.Hospital.RecordHospital;
import org.example.Service.HospitalService;
import org.example.interfaces.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hospital")
public class HospitalController {

    private final HospitalService hospitalService;

    public HospitalController (HospitalService service) {
        this.hospitalService = service;
    }

    @GetMapping
    @PreAuthorize("hasRole('RECEPCAO') || hasRole('PACIENTE') || hasRole('ENFERMEIRA') || hasRole('ADMIN')")
    public ResponseEntity<List<Hospital>> listar(){
        return ResponseEntity.status(200).body(hospitalService.findAll());
    }

    @PostMapping
    @Transactional
<<<<<<< Updated upstream
    @PreAuthorize("hasRole('ADMIN') || hasRole('ENFERMEIRA') || hasRole('RECEPCAO')")
=======
>>>>>>> Stashed changes
    public ResponseEntity cadastrar(@RequestBody RecordHospital dados){
        System.out.println(dados);
        return ResponseEntity.status(201).body(hospitalService.salvar(new Hospital(dados)));
    }

    @PutMapping("/{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity atualizar(@PathVariable Long id , @RequestBody AtualizarHospital dados){
        if (hospitalService.existsById(id)){

            var selectHospital = hospitalService.atualizar(id);
            selectHospital.AtualizaHospital(dados);
            return ResponseEntity.status(200).body(new AtualizarHospital(selectHospital));
        }
        return ResponseEntity.status(404).build();
    }

    @DeleteMapping("{id}")
    @Transactional
    @PreAuthorize("hasRole('ADMIN')")
    public  ResponseEntity DeletaUser(@PathVariable long id){

        hospitalService.deletar(id);

        return ResponseEntity.noContent().build();
    }
}
