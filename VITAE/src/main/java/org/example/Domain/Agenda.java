package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
@Table(name = "Agenda")
@Entity(name = "Agenda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate Horario;
    private int fkUsuario;
    private int fkHospital;
}
