package org.example.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "imagens")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArquivoBanco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;


    @JsonIgnore // ignoramos no JSON pois não faz sentido retornar um vetor de bytes num JSON!
    @Column(length = 50 * 1024 * 1024) // 50 Mega Bytes
    @Lob
    private byte[] foto;

    private Long fkUsuario;

    @ManyToOne
    @JsonIgnore
    private Usuario usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public Long getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Long fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public static class ArquivoBancoBuilder {
        private Integer id;
        private String nome;
        private byte[] foto;
        private Long fkUsuario;
        private Usuario usuario;
        public ArquivoBancoBuilder() {
            // Preencha com valores padrão se necessário
        }

        public ArquivoBancoBuilder id(Integer id) {
            this.id = id;
            return this;
        }

        public ArquivoBancoBuilder nome(String nome) {
            this.nome = nome;
            return this;
        }

        public ArquivoBancoBuilder foto(byte[] foto) {
            this.foto = foto;
            return this;
        }

        public ArquivoBancoBuilder fkUsuario(Long fkUsuario) {
            this.fkUsuario = fkUsuario;
            return this;
        }

        public ArquivoBancoBuilder usuario(Usuario usuario) {
            this.usuario = usuario;
            return this;
        }

        public ArquivoBanco build() {
            ArquivoBanco arquivoBanco = new ArquivoBanco();
            arquivoBanco.setId(this.id);
            arquivoBanco.setNome(this.nome);
            arquivoBanco.setFoto(this.foto);
            arquivoBanco.setFkUsuario(this.fkUsuario);
            arquivoBanco.setUsuario(this.usuario);
            return arquivoBanco;
        }


    }

}
