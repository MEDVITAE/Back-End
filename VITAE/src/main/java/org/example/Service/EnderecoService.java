package org.example.Service;

import org.example.Domain.Agenda;
import org.example.Domain.Endereco;
import org.example.Domain.Hospital;
import org.example.interfaces.EnderecoRepository;
import org.example.interfaces.HospitalRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.repository = enderecoRepository;
    }

    public ResponseEntity <List<Endereco>> findAll(){
        List<Endereco> listaEndereco = repository.findAll();

        if (listaEndereco.isEmpty()){
        ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaEndereco);
    }

    public Endereco salvar(@Validated Endereco endereco) {
        Endereco enderecoSalvo = this.repository.save(endereco);
        return enderecoSalvo;
    }

    public Endereco atualizar(@Validated Long id, Endereco enderecoAtualizado) {
        Endereco endereco = this.buscarPorId(id);
        enderecoAtualizado.setId(endereco.getId());
        return repository.save(enderecoAtualizado);
    }

    public void deletar(Long id) {
        Endereco endereco = this.buscarPorId(id);
        repository.deleteById(id);
    }
    public Endereco buscarPorId(Long id) {
        Optional<Endereco> enderecoOpt = this.repository.findById(id);
        return ResponseEntity.of(enderecoOpt).getBody();
    }
}

