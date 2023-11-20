package org.example.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;
import org.example.interfaces.UsuarioRepository;

import java.util.List;

@Table(name = "Recepcao")
@Entity(name = "Recepcao")
@Getter
@Setter
@NoArgsConstructor
public class Recepcao extends Usuario{

    private String nome;


    public Recepcao(RecordUsuario dados) {
        super(dados.email(), dados.senha(), dados.role(),dados.cpf(),dados.fkHospital());
        this.nome = nome;

    }
    public Recepcao(String nome,int fkHospital) {
        this.nome =  nome;

    }
    public Recepcao(String nome) {
        this.nome =  nome;
    }

    public Recepcao(String email, String senha, UserRole role,  String nome,int fkHospital, String cpf) {
        super(email, senha, role, cpf, fkHospital);
        this.nome = nome;
    }

    @Override
    public void Atualiza(AtualizarUser dados,String senha) {
        super.Atualiza(dados,senha);
        this.nome =  dados.nome();
    }
//   private UsuarioRepository repository;

    @Override
    public List<Usuario> buscarRelatorio(String nome) {
//       var horas = repository.findByName(nome);
        return null;
    }


}
