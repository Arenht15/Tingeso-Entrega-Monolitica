package Tingeso_Entrega1.Services;


import Tingeso_Entrega1.Entities.SavingCapacity;
import Tingeso_Entrega1.Repositories.SavingCapacityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;


public class SavingCapacityServicesTest {
    @Mock //llama a las dependencias a utilizar
    private SavingCapacityRepository scRepository;
    @InjectMocks //Llama al servicio que se va a probar
    private SavingCapacityServices savingCapacityServices;

    @BeforeEach //Inicializa los mocks
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void saveSavingCapacity() {
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(100.0);
        sc.setSavingYears(5);
        sc.setSavingAmountAcum(500.0);
        sc.setSavingHistory(Arrays.asList(50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, 590000.0, 620000.0, 6450000.0));
        sc.setWithdrawalHistory(Arrays.asList(0.0, 15000.0, 4000.0,
                10000.0, 25000.0, 100000.0, 170000.0, 100000.0,
                5000.0, 30000.0, 1000.0, 100000.0));
        sc.setDepositHistory(Arrays.asList(25000.0, 30000.0, 33000.0, 26000.0,
                35000.0, 40000.0, 47000.0, 30000.0, 40000.0, 28000.0, 32000.0, 50000.0));
        when(scRepository.save(sc)).thenReturn(sc);
        assertThat(savingCapacityServices.saveSavingCapacity(sc)).isEqualTo(sc);
    }

    @Test
    void saveSavingCapacity2(){
        SavingCapacity sc1 = new SavingCapacity();
        SavingCapacity sc2 = new SavingCapacity();
        sc1.setScAmount(100.0);
        sc1.setSavingYears(5);
        sc1.setSavingAmountAcum(500.0);
        sc1.setSavingHistory(Arrays.asList(50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, 590000.0, 620000.0, 6450000.0));
        sc2.setScAmount(100.0);
        sc2.setSavingYears(5);
        sc2.setSavingAmountAcum(500.0);
        sc2.setSavingHistory(Arrays.asList(50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, 590000.0, 620000.0, 6450000.0));
        when(scRepository.save(sc1)).thenReturn(sc1);
        when(scRepository.save(sc2)).thenReturn(sc2);
        assertThat(savingCapacityServices.saveSavingCapacity(sc1)).isEqualTo(sc1);
        assertThat(savingCapacityServices.saveSavingCapacity(sc2)).isEqualTo(sc2);
    }
    @Test
    void calculateSavingHistory() {
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(100.0);
        sc.setSavingYears(5);
        sc.setSavingAmountAcum(500.0);
        sc.setSavingHistory(Arrays.asList(50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, 590000.0, 620000.0, 6450000.0));
        sc.setWithdrawalHistory(Arrays.asList(0.0, 15000.0, 4000.0,
                10000.0, 25000.0, 100000.0, 170000.0, 100000.0,
                5000.0, 30000.0, 1000.0, 100000.0));
        assertThat(savingCapacityServices.calculateSavingHistory(sc)).isEqualTo(false);
    }

    @Test
    void calculateSavingHistory2() {
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(100.0);
        sc.setSavingYears(5);
        sc.setSavingAmountAcum(500.0);
        sc.setSavingHistory(Arrays.asList(50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, 590000.0, 620000.0, 6450000.0));
        sc.setWithdrawalHistory(Arrays.asList(0.0, 15000.0, 4000.0,
                150000.0, 25000.0, 100000.0, 170000.0, 100000.0,
                5000.0, 30000.0, 1000.0, 100000.0));
        assertThat(savingCapacityServices.calculateSavingHistory(sc)).isEqualTo(true);
    }

    @Test
    void calculateSavingHistory3() {
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(100.0);
        sc.setSavingYears(5);
        sc.setSavingAmountAcum(500.0);
        sc.setSavingHistory(Arrays.asList(-50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, 590000.0, 620000.0, 6450000.0));
        sc.setWithdrawalHistory(Arrays.asList(0.0, 15000.0, 4000.0,
                10000.0, 25000.0, 100000.0, 170000.0, 100000.0,
                5000.0, 30000.0, 1000.0, 100000.0));
        assertThat(savingCapacityServices.calculateSavingHistory(sc)).isEqualTo(true);
    }
    @Test
    void calculateDepositHistory() {
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(100.0);
        sc.setSavingYears(5);
        sc.setSavingAmountAcum(500.0);
        sc.setSavingHistory(Arrays.asList(50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, 590000.0, 620000.0, 6450000.0));
        sc.setDepositHistory(Arrays.asList(25000.0, 30000.0, 33000.0, 26000.0,
                35000.0, 40000.0, 47000.0, 30000.0, 40000.0, 28000.0, 32000.0, 50000.0));
        assertThat(savingCapacityServices.calculateDepositHistory(sc, 500000.0)).isEqualTo(false);
    }

    @Test
    void calculateDepositHistory2() {
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(100.0);
        sc.setSavingYears(5);
        sc.setSavingAmountAcum(500.0);
        sc.setSavingHistory(Arrays.asList(50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, 590000.0, 620000.0, 6450000.0));
        sc.setDepositHistory(Arrays.asList(15000.0, 30000.0, 33000.0, 26000.0,
                35000.0, 40000.0, 47000.0, 30000.0, 40000.0, 28000.0, 32000.0, 5000.0));
        assertThat(savingCapacityServices.calculateDepositHistory(sc, 500000.0)).isEqualTo(true);
    }

    @Test
    void calculateDepositHistory3() {
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(100.0);
        sc.setSavingYears(5);
        sc.setSavingAmountAcum(500.0);
        sc.setSavingHistory(Arrays.asList(50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, 590000.0, 620000.0, 6450000.0));
        sc.setDepositHistory(Arrays.asList(15000.0, 30000.0, 33000.0, 26000.0,
                35000.0, 40000.0, 47000.0, 30000.0, 40000.0, 28000.0, 32000.0, 50000.0));
        assertThat(savingCapacityServices.calculateDepositHistory(sc, 500000.0)).isEqualTo(false);
    }

    @Test
    void calculateWithdrawals() {
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(100.0);
        sc.setSavingYears(5);
        sc.setSavingAmountAcum(500.0);
        sc.setSavingHistory(Arrays.asList(50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, 590000.0, 620000.0, 6450000.0));
        sc.setWithdrawalHistory(Arrays.asList(0.0, 15000.0, 4000.0,
                25000.0, 35000.0, 40000.0, 47000.0, 50000.0,
                55000.0, 59000.0, 62000.0, 64000.0));
        assertThat(savingCapacityServices.calculateWithdrawals(sc)).isEqualTo(false);
    }

    @Test
    void calculateWithdrawals2() {
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(100.0);
        sc.setSavingYears(5);
        sc.setSavingAmountAcum(500.0);
        sc.setSavingHistory(Arrays.asList(50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, 590000.0, 620000.0, 6450000.0));
        sc.setWithdrawalHistory(Arrays.asList(0.0, 15000.0, 4000.0,
                25000.0, 45000.0, 40000.0, 300000.0, 50000.0,
                55000.0, 59000.0, 62000.0, 70000.0));
        assertThat(savingCapacityServices.calculateWithdrawals(sc)).isEqualTo(true);
    }

    @Test
    void calculateWithdrawals3() {
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(100.0);
        sc.setSavingYears(5);
        sc.setSavingAmountAcum(500.0);
        sc.setSavingHistory(Arrays.asList(50000.0, 100000.0, 200000.0,
                250000.0, 350000.0, 400000.0, 470000.0, 500000.0,
                550000.0, -590000.0, -620000.0, -6450000.0));
        sc.setWithdrawalHistory(Arrays.asList(0.0, 15000.0, 4000.0,
                25000.0, 35000.0, 40000.0, 47000.0, 50000.0,
                55000.0, 59000.0, 62000.0, 70000.0));
        assertThat(savingCapacityServices.calculateWithdrawals(sc)).isEqualTo(false);
    }

}
