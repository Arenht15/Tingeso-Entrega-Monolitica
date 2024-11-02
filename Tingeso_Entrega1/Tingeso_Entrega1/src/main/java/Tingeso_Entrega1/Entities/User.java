package Tingeso_Entrega1.Entities;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long Id;

    @Column(unique = true)
    private String rut;

    private String name;

    private String surname;

    private String email;

    private LocalDate birthdate;

    @Lob
    private byte[] identification;

}
