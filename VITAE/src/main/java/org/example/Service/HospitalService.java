package org.example.Service;

import org.example.Domain.Hospital;
import org.example.interfaces.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    private final HospitalRepository repository;

    public HospitalService(HospitalRepository hospitalRepository) {
        this.repository = hospitalRepository;
    }

    public List<Hospital> findAll(){
        List<Hospital> listaHospital = repository.findAll();

        if (listaHospital.isEmpty()){
            throw new NullPointerException("Lista Vazia");
        }
        return listaHospital;
    }

    public Hospital salvar(Hospital hospital) {
        Hospital hospitalSalvo = this.repository.save(hospital);
        return hospitalSalvo;
    }

    public Hospital atualizar(Long id, Hospital hospitalAtualizado) {
        Hospital hospital = this.buscarPorId(id);
        hospitalAtualizado.setId(hospital.getId());
        return repository.save(hospitalAtualizado);
    }

    public void deletar(int id) {
        Hospital hospital = this.buscarPorId(id);
        repository.deletarHospitalPorId(id);
    }

}
