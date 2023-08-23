package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;

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
    private boolean peso;
    private String altura;
    private boolean tatto;
   
}
