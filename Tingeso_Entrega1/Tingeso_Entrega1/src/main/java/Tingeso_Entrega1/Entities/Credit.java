package Tingeso_Entrega1.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import javax.swing.*;

@Entity
@Table(name = "credit")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Integer type; // type of the credit

    private Double amount; // amount of the credit

    private Integer term; // term of the credit

    private Double rate; // annual credit rate

    private Double financing; // financing of the credit

}
