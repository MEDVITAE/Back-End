package org.example.Service;

import org.example.Domain.Agenda;
import org.example.Domain.Hospital;
import org.example.interfaces.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    private final HospitalRepository repository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.repository = hospitalRepository;
    }

    public ResponseEntity <List<Hospital>> findAll(){
        List<Hospital> listaHospital = this.repository.findAll();
        if (listaHospital.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaHospital);
    }

    public Hospital salvar(@Validated Hospital hospital) {
        Hospital hospitalSalvo = this.repository.save(hospital);
        return hospitalSalvo;
    }

    public Hospital atualizar(@Validated int id, Hospital hospitalAtualizado) {
        Hospital hospital = this.buscarPorId(id);
        hospitalAtualizado.setIdHospital(hospital.getIdHospital());
        return repository.save(hospitalAtualizado);
    }

    public void deletar(int id) {
        Hospital hospital = this.buscarPorId(id);
        repository.deleteById((long) id);
    }
    public Hospital buscarPorId(int id) {
        Optional<Hospital> hospitalOpt = this.repository.findById((long) id);
        return ResponseEntity.of(hospitalOpt).getBody();
    }

}
