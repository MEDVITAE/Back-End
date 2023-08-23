package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "Doacao")
@Entity(name = "Doacao")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")

public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double quantidade;
    private int fkAgenda;
}
