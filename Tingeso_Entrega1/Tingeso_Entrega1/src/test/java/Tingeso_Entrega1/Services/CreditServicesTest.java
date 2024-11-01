package Tingeso_Entrega1.Services;

import Tingeso_Entrega1.Entities.Credit;
import Tingeso_Entrega1.Repositories.CreditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;
public class CreditServicesTest {
     @Mock
     private CreditRepository creditRepository;
     @InjectMocks
     private CreditServices creditServices;

     @BeforeEach //Inicializa los mocks
     void setUp(){
          MockitoAnnotations.openMocks(this);
     }
     @Test
        void saveApplication() {
            Credit credit = new Credit();
            credit.setId_user(1L);
            credit.setAmountDebs(100.0);
            credit.setCuota(5.0);
            credit.setIngress(500.0);
            credit.setStatusDicom(-1);
            credit.setAprovedDebs(1);
            credit.setAprovedFeeIncome(1);
            when(creditRepository.save(credit)).thenReturn(credit);
            assertThat(creditServices.saveCredit(credit)).isEqualTo(credit);
        }
}
