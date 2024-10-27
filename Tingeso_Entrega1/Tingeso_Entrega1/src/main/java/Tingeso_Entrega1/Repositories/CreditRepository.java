package Tingeso_Entrega1.Repositories;

import Tingeso_Entrega1.Entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

}
