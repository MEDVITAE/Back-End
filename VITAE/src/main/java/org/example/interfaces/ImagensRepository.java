package org.example.interfaces;

import org.example.Domain.ArquivoBanco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ImagensRepository extends JpaRepository<ArquivoBanco,Integer> {
    Optional<ArquivoBanco>findByNome(String nome);

    Optional<ArquivoBanco> findByFkUsuario(Long id);
}
