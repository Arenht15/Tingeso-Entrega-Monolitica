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
            credit.setFeeCredit(5.0);
            credit.setIngress(500.0);
            credit.setStatusDicom(-1);
            credit.setAprovedDebs(1);
            credit.setAprovedFeeIncome(1);
            when(creditRepository.save(credit)).thenReturn(credit);
            assertThat(creditServices.saveCredit(credit)).isEqualTo(credit);
        }
        void saveApplication2() {
            Credit credit = new Credit();
            credit.setId_user(1L);
            credit.setAmountDebs(100.0);
            credit.setFeeCredit(5.0);
            credit.setAprovedDebs(1);
            credit.setAprovedFeeIncome(1);
            Credit credit2 = new Credit();
            credit2.setId_user(2L);
            credit2.setAmountDebs(100.0);
            credit2.setFeeCredit(5.0);
            credit2.setAprovedDebs(1);
            credit2.setAprovedFeeIncome(1);
            when(creditRepository.save(credit)).thenReturn(credit);
            when(creditRepository.save(credit2)).thenReturn(credit2);
            assertThat(CreditRepository.getId(credit)).isEqualTo(credit);
            assertThat(creditServices.saveCredit(credit2)).isEqualTo(credit2);
        }



}
