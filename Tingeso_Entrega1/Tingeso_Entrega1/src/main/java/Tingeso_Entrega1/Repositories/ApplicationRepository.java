package Tingeso_Entrega1.Repositories;

import Tingeso_Entrega1.Entities.User;
import Tingeso_Entrega1.Entities.application;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ApplicationRepository extends JpaRepository<application, Long> {
}
