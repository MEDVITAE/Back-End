package org.example.Domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;

import java.util.List;

@Table(name = "ENFERMEIRA")
@Entity(name = "ENFERMEIRA")
@Getter
@Setter
@NoArgsConstructor

public class Enfermeira extends Usuario{

    private String nome;



    public Enfermeira(RecordUsuario dados) {
        super(dados.email(), dados.senha(), dados.role());
        this.nome = nome;

    }
    public Enfermeira(String nome) {

        this.nome = nome;

    }

    public Enfermeira(String email, String encripitando, UserRole role, String nome) {
        super(email, encripitando, role);
        this.nome = nome;
    }

    @Override
    public List<Usuario> buscarRelatorio(String nome) {
        return null;
    }

    @Override
    public void Atualiza(AtualizarUser dados) {
        super.Atualiza(dados);
        this.nome =  dados.nome();

    }
}
