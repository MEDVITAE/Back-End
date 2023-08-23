package org.example.Domain;

import jakarta.persistence.*;

import lombok.*;
import org.example.Records.RecordUsuario;

@Table(name = "Usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")


public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String Email;
    private String senha;
    private String telefone;

    public Usuario(RecordUsuario CadastroMedico) {

        this.nome = CadastroMedico.nome();
        this.Email = CadastroMedico.email();
        this.senha = CadastroMedico.senha();
        this.telefone = CadastroMedico.telefone();
    }
}
