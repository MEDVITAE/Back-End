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
        Hospital hospital = findHospitalById(atualizarHospital.id());

        if (hospital == null || hospital.getIdHospital() == null){
            return null;
        }

        Hospital updateHospital = new Hospital(
                hospital.getIdHospital(),
                atualizarHospital.nome(),
                atualizarHospital.email(),
                atualizarHospital.senha(),
                atualizarHospital.cnpj(),
                atualizarHospital.listEndereco(),
                atualizarHospital.listAgenda()
        );

        return repository.save(updateHospital);
    }

    public Hospital findHospitalById(Long id) {

        Optional<Hospital> hospital = this.repository.findById(id);

        if (hospital.isPresent()){
            return new Hospital(
                    hospital.get().getIdHospital(),
                    hospital.get().getNome(),
                    hospital.get().getEmail(),
                    hospital.get().getSenha(),
                    hospital.get().getCnpj(),
                    hospital.get().getEnderecos(),
                    hospital.get().getAgendamentos()
            );
        }
        return null;
    }

    public Hospital delete(Long id) {
        Hospital hospitalToBeDeleted = findHospitalById(id);

        if (hospitalToBeDeleted == null) {
            return null;
        }

        repository.deleteById(hospitalToBeDeleted.getIdHospital());

        return hospitalToBeDeleted;
    }

}
