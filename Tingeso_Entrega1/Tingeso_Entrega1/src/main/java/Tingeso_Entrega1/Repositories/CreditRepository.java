package Tingeso_Entrega1.Repositories;

import Tingeso_Entrega1.Entities.Credit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CreditRepository extends JpaRepository<Credit, Long> {

    @Query("SELECT c FROM Credit c WHERE c.aprovedApplication = -1 or c.aprovedApplication = 2")
    List<Credit> findCredits();

    @Query("SELECT c FROM Credit c WHERE c.id_user = :userId")
    List<Credit> findCreditsByUserId(@Param("userId") Long userId);
}
