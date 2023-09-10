package org.example.Domain;

import jakarta.persistence.*;
import lombok.Data;
import org.example.Enums.Usuarios.EnumEmail;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "email")

public class  Email  implements Serializable {
    private  static  final long seriaVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEmail ;
    private String ownerRef;
    private String emailFrom;
    private String emailTo;

    private String subject;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendDateEmail;
    private EnumEmail  statusEmail;


}
