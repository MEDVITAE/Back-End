package org.example.Service;

import org.example.Domain.Doacao;
import org.example.interfaces.DoacaoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
public class DoacaoService {

    private final DoacaoRepository repository;

    public DoacaoService(DoacaoRepository doacaoRepository) {
        this.repository = doacaoRepository;
    }

    public Doacao save(@Validated Doacao doacao) {
        Doacao doacaoSalva = this.repository.save(doacao);
        return doacaoSalva;
    }

    public ResponseEntity <List<Doacao>> findAll(){
        List<Doacao> listaDoacao = this.repository.findAll();
        if (listaDoacao.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaDoacao);
    }

    public Doacao buscarDoacaoPorId(Long id) {
        Optional<Doacao> doacaoOpt = this.repository.findById(id);
        return ResponseEntity.of(doacaoOpt).getBody();
    }

    public Doacao updateDoacao(@Validated Long id, Doacao doacaoAtualizada) {
        Doacao doacao = this.buscarDoacaoPorId(id);

        if (doacao == null || doacao.getIdDoacao() == null){
            return null;
        }

        doacaoAtualizada.setIdDoacao(doacao.getIdDoacao());
        return repository.save(doacaoAtualizada);
    }

    public Doacao deletar(Long id) {
        Doacao doacao = this.buscarDoacaoPorId(id);

        if (doacao == null){
            return null;
        }
        repository.deleteById(id);

        return doacao;
    }
}
