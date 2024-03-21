package org.example.Controller;

import jakarta.transaction.Transactional;
import jdk.swing.interop.SwingInterOpUtils;
import org.example.DTO.DestalhesHospDTO;
import org.example.Domain.Endereco;
import org.example.Domain.Hospital;
import org.example.Records.Endereco.RecordEndereco;
import org.example.Records.Hospital.AtualizarHospital;
import org.example.Records.Hospital.RecordHospital;
import org.example.interfaces.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    @GetMapping("/detalhes/{id}")
    public ResponseEntity<DestalhesHospDTO> detahesHosp(@PathVariable int id){
        var detalhes = repository.findByHospitalEndereco(id);
        DestalhesHospDTO hospDTO = new DestalhesHospDTO(detalhes.getNome(), detalhes.getCep(), detalhes.getRua(), detalhes.getNumero(), detalhes.getBairro());
        System.out.println(detalhes.getNome());
        System.out.println(detalhes.getCep());
        System.out.println(detalhes.getRua());
        System.out.println(detalhes.getNumero());
        System.out.println(detalhes.getBairro());

        return ResponseEntity.status(200).body(hospDTO);
    }

    @PostMapping
    @Transactional

    public ResponseEntity cadastrar(@RequestBody RecordHospital dados){
        System.out.println(dados);
        return ResponseEntity.status(201).body(repository.save(new Hospital(dados)));
    }
    @PostMapping("/register/lista")
    public ResponseEntity CadastroLista(@RequestBody List<RecordHospital> dados){
        List<Object> lista= new ArrayList<>();
        for(int i = 0;i < dados.size();i++) {
            Hospital hospital = new Hospital(dados.get(i));
            lista.add(hospital);
            repository.save(hospital);
        }
        return  ResponseEntity.status(200).body(lista);
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
