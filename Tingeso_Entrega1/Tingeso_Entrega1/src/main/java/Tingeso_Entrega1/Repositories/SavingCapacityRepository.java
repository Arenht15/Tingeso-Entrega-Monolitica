package Tingeso_Entrega1.Repositories;

import Tingeso_Entrega1.Entities.SavingCapacity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SavingCapacityRepository extends JpaRepository<SavingCapacity, Long> {

}
