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
    private String cpf;
    private Integer FkHospital;
    @ManyToOne
    @JoinColumn(name = "fkHospitals")
    private Hospital hospital;
    @OneToMany(mappedBy = "fkUsuario", cascade = CascadeType.ALL)
    private List<ArquivoBanco> imagens = new ArrayList<>();


    public Usuario( String email, String senha, UserRole role, String cpf, int fkHospital) {

        this.email = email;
        this.senha = senha;
        this.role = role;
        this.cpf = cpf;
        this.FkHospital = fkHospital;

    }
    public Usuario( String email, String senha, UserRole role, String cpf) {

        this.email = email;
        this.senha = senha;
        this.role = role;
        this.cpf = cpf;
    }
    public Usuario( long id,String email, String senha, UserRole role, String cpf, int fkHospital) {
        this.idUsuario = id;
        this.email = email;
        this.senha = senha;
        this.role = role;
        this.cpf = cpf;
        this.FkHospital = fkHospital;
    }

    protected Usuario() {
    }



    public void Atualiza(AtualizarUser atualizarDados,String senhaCrip) {
        this.email = atualizarDados.email();
        this.senha = senhaCrip;

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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Integer getFkHospital() {
        return FkHospital;
    }

    public void setFkHospital(Integer fkHospital) {
        FkHospital = fkHospital;
    }

    public Hospital getHospital() {
        return hospital;
    }

    public void setHospital(Hospital hospital) {
        this.hospital = hospital;
    }

    public List<ArquivoBanco> getImagens() {
        return imagens;
    }

    public void setImagens(List<ArquivoBanco> imagens) {
        this.imagens = imagens;
    }
}
