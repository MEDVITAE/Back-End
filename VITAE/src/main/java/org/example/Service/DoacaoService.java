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

    public ResponseEntity <List<Doacao>> findAll(){
        List<Doacao> listaDoacao = this.repository.findAll();
        if (listaDoacao.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(listaDoacao);
    }

    public Doacao salvar(@Validated Doacao doacao) {
        Doacao doacaoSalva = this.repository.save(doacao);
        return doacaoSalva;
    }

    public Doacao atualizar(@Validated int id, Doacao doacaoAtualizada) {
        Doacao doacao = this.buscarPorId(id);
        doacaoAtualizada.setIdDoacao(doacao.getIdDoacao());
        return repository.save(doacaoAtualizada);
    }

    public void deletar(int id) {
        Doacao doacao = this.buscarPorId(id);
        repository.deleteById((long) id);
    }

    public Doacao buscarPorId(int id) {
        Optional<Doacao> doacaoOpt = this.repository.findById((long) id);
        return ResponseEntity.of(doacaoOpt).getBody();
    }
}
