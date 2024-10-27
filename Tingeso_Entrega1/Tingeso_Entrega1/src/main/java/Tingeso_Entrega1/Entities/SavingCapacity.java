package Tingeso_Entrega1.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@Table(name = "SavingCapacity")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavingCapacity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private Long idCredit; // id of the user
    private Double ScAmount; // saving capacity of the user
    private Integer savingYears; // years of the saving capacity
    private Double savingAmountAcum; // amount of the saving capacity
    @ElementCollection
    private List<Double> SavingHistory; // saving history of the user
    @ElementCollection
    private List<Double> WithdrawalHistory; // saving history of the user
    @ElementCollection
    private List<Double> DepositHistory; // saving history of the user
}
