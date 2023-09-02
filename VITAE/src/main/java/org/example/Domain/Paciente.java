package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;

@Table(name = "Paciente")
@Entity(name = "Paciente")
@Getter
@Setter
@NoArgsConstructor
public class Paciente extends Usuario{

    private String nome;

    private Long fkCaracteristicas;

    public Paciente(String nome) {
        this.nome = nome;
    }

    public Paciente(String email, String senha, UserRole role, String nome, Long fkCaracteristicas) {
        super(email, senha, role);
        this.nome = nome;
        this.fkCaracteristicas = fkCaracteristicas;
    }

    public Paciente(RecordUsuario dados) {
        super(dados.email(), dados.senha(), dados.role());
        this.nome = dados.nome();


    }


    @Override
    public void Atualiza(AtualizarUser dados) {
        super.Atualiza(dados);
        this.nome =  dados.nome();

    }
}
