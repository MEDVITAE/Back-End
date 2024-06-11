package org.example.interfaces;

import org.example.Domain.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EnderecoRepository extends JpaRepository<Endereco,Long> {
    @Query("select e from endereco e where fkUsuario = null")
    List<RecuperaCeps> findAllCep();


    Endereco findByFkUsuario(Long id);
}
