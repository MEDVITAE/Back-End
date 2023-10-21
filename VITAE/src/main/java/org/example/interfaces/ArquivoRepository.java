package org.example.interfaces;


import org.example.Domain.Arquivo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArquivoRepository extends JpaRepository<Arquivo, Integer> {
    }

