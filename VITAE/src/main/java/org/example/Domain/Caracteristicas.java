package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.Records.Caracteristicas.AtualizaCaracteristicas;
import org.example.Records.Caracteristicas.RecordCaracteristicas;

import java.util.Date;

@Table(name = "Caracteristicas")
@Entity(name = "Caracteristicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Caracteristicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String peso;
    private String altura;
    private boolean tatto;
    private String sexo;
    private Date dtNascimento;
    private Long fkUsuario;

    public Caracteristicas(RecordCaracteristicas dados) {
        this.peso = dados.peso();
        this.altura = dados.altura();
        this.tatto = dados.tatto();
        this.sexo = dados.sexo();
        this.dtNascimento = dados.dtNascimento();
        this.fkUsuario = dados.fkUsuario();
    }

    public void AtualizaCaracteristicas(AtualizaCaracteristicas dados) {
        this.peso = dados.peso();
        this.altura = dados.altura();
        this.tatto = dados.tatto();
        this.sexo = dados.sexo();
        this.dtNascimento = dados.dtNascimento();
        this.fkUsuario = dados.fkUsuario();
    }
}
