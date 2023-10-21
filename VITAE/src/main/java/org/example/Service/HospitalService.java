package org.example.Service;

import jakarta.transaction.Transactional;
import org.example.Domain.Hospital;
import org.example.Records.Hospital.AtualizarHospital;
import org.example.interfaces.HospitalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalService {

    @Autowired
    private final HospitalRepository repository;

    public HospitalService(HospitalRepository repository) {
        this.repository = repository;
    }

    public Hospital save(Hospital hospital) {
        return repository.save(hospital);
    }

    public List<Hospital> findAll(){
        return repository.findAll();
    }

    public Hospital updateHospital(AtualizarHospital atualizarHospital) {
        Long hospitalId = findHospitalById(atualizarHospital.id());

        if (hospitalId == null){
            return null;
        }

        Hospital updateHospital = new Hospital(
                atualizarHospital.id(),
                atualizarHospital.nome(),
                atualizarHospital.email(),
                atualizarHospital.senha(),
                atualizarHospital.cnpj(),
                atualizarHospital.listEndereco(),
                atualizarHospital.listAgenda()
        );

        return repository.save(updateHospital);
    }

    public Long findHospitalById(Long id) {

        Optional<Hospital> hospital = this.repository.findById(id);

        if (hospital.isPresent()){
            return hospital.get().getIdHospital();
        }
        return null;
    }

    @Transactional
    public String delete(Long id) {
        Long idHospital = this.findHospitalById(id);

        if (idHospital == null) {
            return null;
        }

        repository.deleteById(idHospital);

        return idHospital.toString();
    }

}
