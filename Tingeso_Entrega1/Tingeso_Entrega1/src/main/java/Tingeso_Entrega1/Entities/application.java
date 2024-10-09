package Tingeso_Entrega1.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import java.util.List;

@Entity
@Table(name = "application")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private Long id_user;

    private byte[] ingress;

    private byte[] histDicom;

    private byte[] paySheet;

    private byte[] jobCertificate;



}
