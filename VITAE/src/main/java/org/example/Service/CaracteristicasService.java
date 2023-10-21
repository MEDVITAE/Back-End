package org.example.Service;

import org.example.Domain.Caracteristicas;
import org.example.interfaces.CaractetisticasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
public class CaracteristicasService {

        @Autowired
        private final CaractetisticasRepository repository;

        public CaracteristicasService(CaractetisticasRepository caractetisticasRepository) {
            this.repository = caractetisticasRepository;
        }

        public List<Caracteristicas> listaCaracteristicas(){
            List<Caracteristicas> listaCaracteristicas = repository.findAll();
            if (listaCaracteristicas.isEmpty()){
                ResponseEntity.noContent().build();
            }
            return listaCaracteristicas;
        }

        public Caracteristicas salvar(@Validated Caracteristicas caracteristicas) {
            Caracteristicas caracteristicasSalvas = this.repository.save(caracteristicas);
            return caracteristicasSalvas;
        }

        public Caracteristicas atualizar(@Validated long id, Caracteristicas caracteristicasAtualizada) {
            Caracteristicas caracteristicas = this.buscarPorId(id);
            caracteristicasAtualizada.setIdCaracteristicas(caracteristicas.getIdCaracteristicas());
            return repository.save(caracteristicasAtualizada);
        }

        public void deletar(long id) {
            Caracteristicas caracteristicas = this.buscarPorId(id);
            repository.deleteById(id);
        }

        public Caracteristicas buscarPorId(long id) {
            Optional<Caracteristicas> caracteristicasOpt = this.repository.findById(id);
            return ResponseEntity.of(caracteristicasOpt).getBody();
    }
    }


