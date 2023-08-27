package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.Records.Endereco.AtualizaEndereco;
import org.example.Records.Endereco.RecordEndereco;

@Table(name = "Endereco")
@Entity(name = "Endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")

public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cidade;
    private String bairro;
    private String cep;
    private String logradouro;
    private String rua;
    private int numero;
    private Long fkUsuario;
    private Long fkHospital;

    public Endereco(RecordEndereco atualizar) {

        this.cidade = atualizar.cidade();
        this.bairro = atualizar.bairro();
        this.cep = atualizar.cep();
        this.logradouro = atualizar.logradouro();
        this.rua = atualizar.rua();
        this.numero = atualizar.numero();
        this.fkUsuario = atualizar.fkUsuario();
        this.fkHospital = atualizar.fkHospital();

    }
    public void AtualizaEndereco(AtualizaEndereco atualizar){
        this.cidade = atualizar.cidade();
        this.bairro = atualizar.bairro();
        this.cep = atualizar.cep();
        this.logradouro = atualizar.logradouro();
        this.rua = atualizar.rua();
        this.numero = atualizar.numero();
        this.fkUsuario = atualizar.fkUsuario();
        this.fkHospital = atualizar.fkHospital();
    }
}
