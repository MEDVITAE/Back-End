package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Table(name = "Caracteristicas")
@Entity(name = "Caracteristicas")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Caracteristicas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double peso;
    private Double altura;
    private boolean tatto;
    private String sexo;
    private Date dtNascimento;
    private Long fkUsuario;

   
}
