package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;

import java.util.ArrayList;
import java.util.List;

@Table(name = "Paciente")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
public class Paciente extends Usuario{

    private String nome;


    @OneToMany
    @JoinColumn(name = "fkUsuario",unique = true)
    private List<Caracteristicas> fkCaracteristicas = new ArrayList<>(1);

    public Paciente(String nome) {
        this.nome = nome;
    }

    public Paciente(Long id, String nome,String email, String senha, UserRole role, String cpf, int fkHospital) {
        super(id,email, senha, role, cpf, fkHospital);
        this.nome = nome;
    }
    public Paciente(String email, String senha, UserRole role, String cpf, int fkHospital, String nome) {
        super(email, senha, role, cpf, fkHospital);
        this.nome = nome;
    }

    public Paciente(RecordUsuario dados) {
        super(dados.email(), dados.senha(), dados.role(), dados.cpf(), dados.fkHospital());
        this.nome = dados.nome();

    }



    @Override
    public List<Usuario> buscarRelatorio(String nome) {
        return null;
    }

    @Override
    public void Atualiza(AtualizarUser dados,String senha) {
        super.Atualiza(dados,senha);
        this.nome =  dados.nome();
    }
}
