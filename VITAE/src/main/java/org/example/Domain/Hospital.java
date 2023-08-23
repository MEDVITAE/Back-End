package org.example.Domain;

import jakarta.persistence.*;
import lombok.*;
@Table(name = "Hospital")
@Entity(name = "Hospital")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of= "id")
public class Hospital {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String Email;
    private String senha;

}
