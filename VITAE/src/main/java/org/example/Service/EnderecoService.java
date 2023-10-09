package org.example.Service;

import org.example.Domain.Endereco;
import org.example.Domain.Hospital;
import org.example.interfaces.EnderecoRepository;
import org.example.interfaces.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnderecoService {

    private final EnderecoService repository;

    public EnderecoService(EnderecoRepository enderecoRepository) {
        this.repository = enderecoRepository;
    }

    public List<Endereco> findAll(){
        List<Endereco> listaEndereco = repository.findAll();

        if (listaEndereco.isEmpty()){
            throw new NullPointerException("Lista Vazia");
        }
        return listaEndereco;
    }

    public Endereco salvar(Endereco endereco) {
        Endereco enderecoSalvo = this.repository.save(endereco);
        return enderecoSalvo;
    }

    public Endereco atualizar(Long id, Endereco enderecoAtualizado) {
        Endereco endereco = this.buscarPorId(id);
        enderecoAtualizado.setId(endereco.getId());
        return repository.save(enderecoAtualizado);
    }

    public void deletar(int id) {
        Endereco endereco = this.buscarPorId(id);
        repository.deletarEnderecoPorId(id);
    }
}

