package org.example.interfaces;

import org.example.Domain.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoacaoRepository extends JpaRepository<Doacao,Long> {
}
