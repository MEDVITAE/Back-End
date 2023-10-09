package org.example.Service;

import org.example.Domain.Doacao;
import org.example.Domain.Hospital;
import org.example.interfaces.DoacaoRepository;
import org.example.interfaces.HospitalRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DoacaoService {


    private final DoacaoRepository repository;

    public DoacaoService(DoacaoRepository doacaoRepository) {
        this.repository = doacaoRepository;
    }

    public List<Doacao> findAll(){
        List<Doacao> listaDoacao = repository.findAll();

        if (listaDoacao.isEmpty()){
            throw new NullPointerException("Lista Vazia");
        }
        return listaDoacao;
    }

    public Doacao salvar(Doacao doacao) {
        Doacao doacaoSalva = this.repository.save(doacao);
        return doacaoSalva;
    }

    public Doacao atualizar(Long id, Doacao doacaoAtualizada) {
        Doacao doacao = this.buscarPorId(id);
        doacaoAtualizada.setId(doacao.getId());
        return repository.save(doacaoAtualizada);
    }

    public void deletar(int id) {
        Doacao doacao = this.buscarPorId(id);
        repository.deletarDoacaoPorId(id);
    }

}
