package Tingeso_Entrega1.Repositories;

import Tingeso_Entrega1.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByRut(String rut);
}
