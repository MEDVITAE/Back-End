package org.example.interfaces;

import org.example.Domain.Doacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DoacaoRepository extends JpaRepository<Doacao,Long> {
    @Query(value = "SELECT u.id_usuario, u.nome, SUM(d.quantidade) as quantidade\n" +
            "FROM usuario u\n" +
            "JOIN agenda a ON u.id_usuario = a.fk_usuario\n" +
            "JOIN doacao d ON a.id_agenda = d.fk_agenda\n" +
            "GROUP BY u.id_usuario, u.nome\n" +
            "ORDER BY quantidade DESC limit 5;",nativeQuery = true)
    List<RecuperaValoresDoacao> findAllTop10();
    @Query(value = "SELECT COUNT(*) + 1 AS posicao\n" +
            "FROM (\n" +
            "    SELECT u.id_usuario, SUM(d.quantidade) AS total_doado\n" +
            "    FROM usuario u\n" +
            "    JOIN agenda a ON u.id_usuario = a.fk_usuario\n" +
            "    JOIN doacao d ON a.id_agenda = d.fk_agenda\n" +
            "    GROUP BY u.id_usuario\n" +
            ") subquery\n" +
            "WHERE subquery.total_doado > (\n" +
            "    SELECT SUM(d2.quantidade) \n" +
            "    FROM usuario u2\n" +
            "    JOIN agenda a2 ON u2.id_usuario = a2.fk_usuario\n" +
            "    JOIN doacao d2 ON a2.id_agenda = d2.fk_agenda\n" +
            "    WHERE u2.id_usuario = :id\n" +
            ");",nativeQuery = true)
    RecuperaPosicao posicao( int id);
}
