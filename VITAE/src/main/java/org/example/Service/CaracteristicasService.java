package org.example.Service;

import org.example.Domain.Caracteristicas;
import org.example.Domain.Hospital;
import org.example.interfaces.CaractetisticasRepository;
import org.example.interfaces.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristicasService {



        private final CaractetisticasRepository repository;

        public CaracteristicasService(CaractetisticasRepository caractetisticasRepository) {
            this.repository = caractetisticasRepository;
        }

        public List<Caracteristicas> findAll(){
            List<Caracteristicas> listaCaracteristicas = repository.findAll();

            if (listaCaracteristicas.isEmpty()){
                throw new NullPointerException("Lista Vazia");
            }
            return listaCaracteristicas;
        }

        public Caracteristicas salvar(Caracteristicas caracteristicas) {
            Caracteristicas caracteristicasSalvas = this.repository.save(caracteristicas);
            return caracteristicasSalvas;
        }

        public Caracteristicas atualizar(Long id, Caracteristicas caracteristicasAtualizada) {
            Caracteristicas caracteristicas = this.buscarPorId(id);
            caracteristicasAtualizada.setId(caracteristicas.getId());
            return repository.save(caracteristicasAtualizada);
        }

        public void deletar(int id) {
            Caracteristicas caracteristicas = this.buscarPorId(id);
            repository.deletarCaracteristicasPorId(id);
        }

    }


