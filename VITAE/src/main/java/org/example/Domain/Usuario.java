package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.Enums.Usuarios.UserRole;
import org.example.Records.Autorizacao.recordRegister;
import org.example.Records.Hospital.AtualizarHospital;
import org.example.Records.Hospital.RecordHospital;
import org.example.Records.Usuario.AtualizarUser;
import org.example.Records.Usuario.RecordUsuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Table(name = "Usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(name = "Email")
    private String email;
    private String senha;
    private String telefone;
    @Enumerated(EnumType.STRING)
    private UserRole role;


    public Usuario(String email, String senha, UserRole role){
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    public Usuario(RecordUsuario dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.senha = dados.senha();
        this.telefone = dados.telefone();

    }

    public void Atualiza(AtualizarUser atualizarDados) {
        this.nome = atualizarDados.nome();
        this.email = atualizarDados.email();
        this.senha = atualizarDados.senha();
        this.telefone = atualizarDados.telefone();

    }




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(
                      new SimpleGrantedAuthority("ROLE_ADMIN")
                    , new SimpleGrantedAuthority("ROLE_EMFERMEIRA")
                    , new SimpleGrantedAuthority("ROLE_RECEPCAO"));

        else if (this.role == UserRole.EMFERMEIRA)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_EMFERMERA"));

        else if (this.role == UserRole.RECEPCAO)
            return List.of(new SimpleGrantedAuthority("ROLE_RECEPCAO"));

        else return List.of(new SimpleGrantedAuthority("ROLE_PACIENTE"));


    }
    @Override
    public String getPassword() {
        return senha; // Retorne a senha do usuário
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
