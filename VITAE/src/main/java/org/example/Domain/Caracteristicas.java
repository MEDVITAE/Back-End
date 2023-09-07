package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.Records.Caracteristicas.AtualizaCaracteristicas;
import org.example.Records.Caracteristicas.RecordCaracteristicas;

import java.util.Date;

@Table(name = "Caracteristicas")
@Entity(name = "Caracteristicas")

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "idCaracteristicas")
public class Caracteristicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idCaracteristicas;
    @Getter
    @Setter
    private String peso;
    @Getter
    @Setter
    private String altura;
    @Getter
    @Setter
    private boolean tatto;
    @Getter
    @Setter
    private String sexo;
    @Getter
    @Setter
    private Date dtNascimento;
    @Getter
    @Setter
    private  Long fkUsuario;
    @ManyToOne
    @JoinColumn(name = "fkUsuario", referencedColumnName = "idUsuario",insertable = false, updatable = false)
    private Usuario usuario;








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
