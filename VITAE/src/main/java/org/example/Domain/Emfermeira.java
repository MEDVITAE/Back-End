package org.example.Domain;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;

@Table(name = "EMFERMEIRA")
@Entity(name = "EMFERMEIRA")
@Getter
@Setter
@NoArgsConstructor

public class Emfermeira extends Usuario{

    private String nome;



    public Emfermeira(RecordUsuario dados) {
        super(dados.email(), dados.senha(), dados.role());
        this.nome = nome;

    }
    public Emfermeira(String nome) {

        this.nome = nome;

    }

    public Emfermeira(String email, String encripitando, UserRole role, String nome) {
        super(email, encripitando, role);
        this.nome = nome;
    }

    @Override
    public void Atualiza(AtualizarUser dados) {
        super.Atualiza(dados);
        this.nome =  dados.nome();

    }
}
