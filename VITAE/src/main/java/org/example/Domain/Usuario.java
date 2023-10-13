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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Table(name = "Usuario")
@Entity(name = "Usuario")
@Getter
@Setter
@EqualsAndHashCode(of= "id")
public  abstract class Usuario implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUsuario;
    @Column(name = "Email")
    private String email;
    private String senha;

    @Enumerated(EnumType.STRING)
    private UserRole role;




    public Usuario(String email, String senha, UserRole role){
        this.email = email;
        this.senha = senha;
        this.role = role;
    }

    protected Usuario() {
    }


    public void Atualiza(AtualizarUser atualizarDados) {
        this.email = atualizarDados.email();
        this.senha = atualizarDados.senha();
    }
    public abstract List<Usuario> buscarRelatorio(String nome);




    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (this.role == UserRole.ADMIN)
            return List.of(
                      new SimpleGrantedAuthority("ROLE_ADMIN")
                    , new SimpleGrantedAuthority("ROLE_ENFERMEIRA")
                    , new SimpleGrantedAuthority("ROLE_RECEPCAO"));

        else if (this.role == UserRole.ENFERMEIRA)
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ENFERMEIRA"));

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
