package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.Records.Hospital.AtualizarHospital;
import org.example.Records.Hospital.RecordHospital;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Hospital")
@Entity(name = "Hospital")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHospital;
    private String nome;
    private String email;
    private String senha;
    private String cnpj;
    @OneToMany(mappedBy = "fkHospital")
    private List<Endereco> enderecos = new ArrayList<>();
    @OneToMany(mappedBy = "fkHospital")
    private List<Agenda> agendamentos = new ArrayList<>();



    public Hospital(RecordHospital cadastroHospital) {
        this.nome = cadastroHospital.nome();
        this.email = cadastroHospital.email();
        this.senha = cadastroHospital.senha();
        this.cnpj = cadastroHospital.cnpj();
    }

    public void AtualizaHospital(AtualizarHospital atualizarDados) {
        this.nome = atualizarDados.nome();
        this.email = atualizarDados.email();
        this.senha = atualizarDados.senha();
        this.cnpj = atualizarDados.cnpj();
    }
}
