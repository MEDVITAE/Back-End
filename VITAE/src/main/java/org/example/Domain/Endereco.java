package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Endereco")
@Entity(name = "Endereco")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")

public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cidade;
    private String bairro;
    private String cep;
    private String logradouro;
    private String rua;
    private int numero;

}
