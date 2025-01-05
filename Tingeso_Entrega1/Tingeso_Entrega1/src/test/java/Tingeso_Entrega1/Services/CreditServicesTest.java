package Tingeso_Entrega1.Services;

import Tingeso_Entrega1.Entities.Credit;
import Tingeso_Entrega1.Entities.SavingCapacity;
import Tingeso_Entrega1.Repositories.CreditRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
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
     @Mock
     private SavingCapacityServices scServices;
     @Mock
     private CreditServices creditServicesMock;

     @BeforeEach //Inicializa los mocks
     void setUp(){
          MockitoAnnotations.openMocks(this);
     }

    @Test
    void getCredits(){
        Credit credit1 = new Credit();
        Credit credit2 = new Credit();
        credit1.setId_savingCapacity(1L);
        credit1.setId_user(1L);
        credit1.setAmountDebs(100.0);
        credit1.setCuota(5.0);
        credit1.setIngress(500.0);
        credit1.setStatusDicom(-1);
        credit1.setAprovedDebs(1);
        credit1.setAprovedFeeIncome(1);
        credit2.setId_savingCapacity(1L);
        credit2.setId_user(1L);
        credit2.setAmountDebs(100.0);
        credit2.setCuota(5.0);
        credit2.setIngress(500.0);
        credit2.setStatusDicom(-1);
        credit2.setAprovedDebs(1);
        credit2.setAprovedFeeIncome(1);
        when(creditRepository.findCredits()).thenReturn(List.of(credit1, credit2));
        assertThat(creditServices.getCredits()).isEqualTo(List.of(credit1, credit2));
    }

    @Test
    void searchCredit(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setCuota(5.0);
        credit.setIngress(500.0);
        credit.setStatusDicom(-1);
        credit.setAprovedDebs(1);
        credit.setAprovedFeeIncome(1);
        when(creditRepository.findById(1L)).thenReturn(Optional.of(credit));
        assertThat(creditServices.searchCredit(1L)).isEqualTo(credit);
    }

    @Test
    void searchCredit2(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setCuota(5.0);
        credit.setIngress(500.0);
        credit.setStatusDicom(-1);
        credit.setAprovedDebs(1);
        credit.setAprovedFeeIncome(1);
        when(creditRepository.findById(1L)).thenReturn(Optional.of(credit));
        assertThat(creditServices.searchCredit(2L)).isEqualTo(null);
    }

    @Test
    void findCreditsByUserId(){
        Credit credit1 = new Credit();
        Credit credit2 = new Credit();
        credit1.setId_savingCapacity(1L);
        credit1.setId_user(1L);
        credit1.setAmountDebs(100.0);
        credit1.setCuota(5.0);
        credit1.setIngress(500.0);
        credit1.setStatusDicom(-1);
        credit2.setId_savingCapacity(1L);
        credit2.setId_user(1L);
        credit2.setAmountDebs(100.0);
        credit2.setCuota(5.0);
        credit2.setIngress(500.0);
        credit2.setStatusDicom(-1);
        when(creditRepository.findCreditsByUserId(1L)).thenReturn(List.of(credit1, credit2));
        assertThat(creditServices.findCreditsByUserId(1L)).isEqualTo(List.of(credit1, credit2));
    }

    @Test
    void feeIncome(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setCuota(5.0);
        credit.setIngress(500.0);
        credit.setStatusDicom(-1);
        credit.setAprovedDebs(1);
        credit.setAprovedFeeIncome(1);
        credit = creditServices.feeIncome(credit);
        assertThat(credit.getAprovedFeeIncome()).isEqualTo(1);
    }

    @Test
    void feeIncome2(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setCuota(5.0);
        credit.setIngress(10.0);
        credit.setStatusDicom(-1);
        credit.setAprovedDebs(1);
        credit = creditServices.feeIncome(credit);
        assertThat(credit.getAprovedFeeIncome()).isEqualTo(0);
    }

    @Test
    void creditHistory(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setCuota(5.0);
        credit.setIngress(500.0);
        credit.setStatusDicom(0);
        credit.setAprovedDebs(1);
        credit.setAprovedFeeIncome(1);
        credit = creditServices.creditHistory(credit);
        assertThat(credit.getStatusDicom()).isEqualTo(0);
    }

    @Test
    void creditHistory2(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setCuota(5.0);
        credit.setIngress(500.0);
        credit.setStatusDicom(1);
        credit.setAprovedDebs(1);
        credit.setAprovedFeeIncome(1);
        credit = creditServices.creditHistory(credit);
        assertThat(credit.getStatusDicom()).isEqualTo(1);
    }


    @Test
    void WorkSituacion(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setTypeJob(0);
        credit.setSeniority(2);
        credit = creditServices.WorkSituacion(credit);
        assertThat(credit.getAprovedEmployed()).isEqualTo(1);
    }

    @Test
    void WorkSituacion2(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setTypeJob(0);
        credit.setSeniority(0);
        credit = creditServices.WorkSituacion(credit);
        assertThat(credit.getAprovedEmployed()).isEqualTo(0);
    }

    @Test
    void WorkSituacion3(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setTypeJob(1);
        credit.setSeniority(0);
        credit.setIngressAcum(6100000.0);
        credit.setSeniority(2);
        credit = creditServices.WorkSituacion(credit);
        assertThat(credit.getAprovedEmployed()).isEqualTo(1);
    }

    @Test
    void WorkSituacion4(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setTypeJob(1);
        credit.setSeniority(0);
        credit.setIngressAcum(5900000.0);
        credit.setSeniority(2);
        credit = creditServices.WorkSituacion(credit);
        assertThat(credit.getAprovedEmployed()).isEqualTo(0);
    }

    @Test
    void Debs(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(0.0);
        credit.setCuota(5.0);
        credit.setIngress(500.0);
        credit = creditServices.Debs(credit);
        assertThat(credit.getAprovedDebs()).isEqualTo(1);
    }

    @Test
    void Debs2(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setCuota(5.0);
        credit.setIngress(500.0);
        credit = creditServices.Debs(credit);
        assertThat(credit.getAprovedDebs()).isEqualTo(1);
    }

    @Test
    void Debs3(){
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(100.0);
        credit.setCuota(5.0);
        credit.setIngress(200.0);
        credit = creditServices.Debs(credit);
        assertThat(credit.getAprovedDebs()).isEqualTo(0);
    }

    @Test
    void financingCredit(){
        Credit credit = new Credit();
        credit.setIngress(500.0);
        credit.setAmount(1000000.0);
        credit.setType(1);
        credit.setPorcent(80.0);
        credit = creditServices.financingCredit(credit);
        assertThat(credit.getAmountApproved()).isEqualTo(1);
    }

    @Test
    void financingCredit2(){
        Credit credit = new Credit();
        credit.setIngress(500.0);
        credit.setAmount(1000000.0);
        credit.setType(1);
        credit.setPorcent(90.0);
        credit = creditServices.financingCredit(credit);
        assertThat(credit.getAmountApproved()).isEqualTo(0);
    }

    @Test
    void financingCredit3(){
        Credit credit = new Credit();
        credit.setIngress(500.0);
        credit.setAmount(1000000.0);
        credit.setType(2);
        credit.setPorcent(70.0);
        credit = creditServices.financingCredit(credit);
        assertThat(credit.getAmountApproved()).isEqualTo(1);
    }
    @Test
    void financingCredit4(){
        Credit credit = new Credit();
        credit.setIngress(500.0);
        credit.setAmount(1000000.0);
        credit.setType(2);
        credit.setPorcent(80.0);
        credit = creditServices.financingCredit(credit);
        assertThat(credit.getAmountApproved()).isEqualTo(0);
    }

    @Test
    void financingCredit5(){
        Credit credit = new Credit();
        credit.setIngress(500.0);
        credit.setAmount(1000000.0);
        credit.setType(3);
        credit.setPorcent(60.0);
        credit = creditServices.financingCredit(credit);
        assertThat(credit.getAmountApproved()).isEqualTo(1);
    }

    @Test
    void financingCredit6(){
        Credit credit = new Credit();
        credit.setIngress(500.0);
        credit.setAmount(1000000.0);
        credit.setType(3);
        credit.setPorcent(70.0);
        credit = creditServices.financingCredit(credit);
        assertThat(credit.getAmountApproved()).isEqualTo(0);
    }

    @Test
    void financingCredit7(){
        Credit credit = new Credit();
        credit.setIngress(500.0);
        credit.setAmount(1000000.0);
        credit.setType(4);
        credit.setPorcent(50.0);
        credit = creditServices.financingCredit(credit);
        assertThat(credit.getAmountApproved()).isEqualTo(1);
    }

    @Test
    void financingCredit8(){
        Credit credit = new Credit();
        credit.setIngress(500.0);
        credit.setAmount(1000000.0);
        credit.setType(4);
        credit.setPorcent(60.0);
        credit = creditServices.financingCredit(credit);
        assertThat(credit.getAmountApproved()).isEqualTo(0);
    }

    @Test
    void Verifyyears(){
        Credit credit = new Credit();
        credit.setYears(25);
        credit.setTerm(12);
        credit = creditServices.Verifyyears(credit);
        assertThat(credit.getAprovedYears()).isEqualTo(1);
    }

    @Test
    void Verifyyears2(){
        Credit credit = new Credit();
        credit.setYears(46);
        credit.setTerm(25);
        credit = creditServices.Verifyyears(credit);
        assertThat(credit.getAprovedYears()).isEqualTo(0);
    }

    @Test
    void Verifyyears3(){
        Credit credit = new Credit();
        credit.setYears(15);
        credit.setTerm(12);
        credit = creditServices.Verifyyears(credit);
        assertThat(credit.getAprovedYears()).isEqualTo(0);
    }

    @Test
    void calculateYears(){
        int years = creditServices.calculateYears(LocalDate.parse("2000-01-01"));
        assertThat(years).isEqualTo(24);
    }

    @Test
    void SavingCapacity() {
        // Arrange
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setAmount(1000.0);
        credit.setIngress(200.0); // Ejemplo de ingreso

        SavingCapacity savingCapacity = new SavingCapacity();
        savingCapacity.setScAmount(900.0); // Mayor al 10% de 1000.0
        savingCapacity.setSavingYears(3); // Más de 2 años
        savingCapacity.setSavingAmountAcum(150.0); // Menor al 10% de 1000.0
        savingCapacity.setSavingHistory(Arrays.asList(100.0, 200.0));
        savingCapacity.setWithdrawalHistory(Arrays.asList(50.0));
        savingCapacity.setDepositHistory(Arrays.asList(300.0));

        when(scServices.searchSavingCapacity(1L)).thenReturn(savingCapacity);
        when(scServices.calculateSavingHistory(savingCapacity)).thenReturn(false);
        when(scServices.calculateDepositHistory(savingCapacity, credit.getIngress())).thenReturn(false);
        when(scServices.calculateWithdrawals(savingCapacity)).thenReturn(false);

        Credit result = creditServices.SavingCapacity(credit);
        assertThat(result.getAprovedSavingCapacity()).isEqualTo(1);
    }

    @Test
    void SavingCapacity2() {
        // Arrange
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setAmount(1000.0);
        credit.setIngress(200.0); // Ejemplo de ingreso

        SavingCapacity savingCapacity = new SavingCapacity();
        savingCapacity.setScAmount(900.0); // Mayor al 10% de 1000.0
        savingCapacity.setSavingYears(3); // Más de 2 años
        savingCapacity.setSavingAmountAcum(80.0); // Menor al 10% de 1000.0
        savingCapacity.setSavingHistory(Arrays.asList(100.0, 200.0));
        savingCapacity.setWithdrawalHistory(Arrays.asList(50.0));
        savingCapacity.setDepositHistory(Arrays.asList(300.0));

        when(scServices.searchSavingCapacity(1L)).thenReturn(savingCapacity);
        when(scServices.calculateSavingHistory(savingCapacity)).thenReturn(true);
        when(scServices.calculateDepositHistory(savingCapacity, credit.getIngress())).thenReturn(true);
        when(scServices.calculateWithdrawals(savingCapacity)).thenReturn(true);

        Credit result = creditServices.SavingCapacity(credit);
        assertThat(result.getAprovedSavingCapacity()).isEqualTo(0);
    }

    @Test
    void SavingCapacity3() {
        // Arrange
        Credit credit = new Credit();
        credit.setId_savingCapacity(1L);
        credit.setAmount(1000.0);
        credit.setIngress(200.0); // Ejemplo de ingreso

        SavingCapacity savingCapacity = new SavingCapacity();
        savingCapacity.setScAmount(900.0);
        savingCapacity.setSavingYears(0);
        savingCapacity.setSavingAmountAcum(80.0);
        savingCapacity.setSavingHistory(Arrays.asList(100.0, 200.0));
        savingCapacity.setWithdrawalHistory(Arrays.asList(50.0));
        savingCapacity.setDepositHistory(Arrays.asList(300.0));

        when(scServices.searchSavingCapacity(1L)).thenReturn(savingCapacity);
        when(scServices.calculateSavingHistory(savingCapacity)).thenReturn(true);
        when(scServices.calculateDepositHistory(savingCapacity, credit.getIngress())).thenReturn(true);
        when(scServices.calculateWithdrawals(savingCapacity)).thenReturn(true);

        Credit result = creditServices.SavingCapacity(credit);
        assertThat(result.getAprovedSavingCapacity()).isEqualTo(0);
    }

    @Test
    void calculateCredit() {
        Credit credit = new Credit();
        credit.setCuota(5.0);
        credit.setIngress(500.0);
        credit.setId_savingCapacity(1L);
        credit.setAmountDebs(0.0);
        credit.setStatusDicom(-1);
        credit.setAprovedDebs(1);
        credit.setAprovedFeeIncome(1);
        credit.setYears(25);
        credit.setTerm(12);
        credit.setAprovedEmployed(1);
        credit.setAprovedYears(1);
        credit.setAprovedSavingCapacity(1);
        credit.setTypeJob(1);
        credit.setSeniority(2);
        credit.setIngressAcum(6100000.0);
        credit.setAmount(1000000.0);
        credit.setType(1);
        credit.setPorcent(80.0);

        SavingCapacity savingCapacity = new SavingCapacity();
        savingCapacity.setScAmount(900.0);
        savingCapacity.setSavingYears(0);
        savingCapacity.setSavingAmountAcum(80.0);
        savingCapacity.setSavingHistory(Arrays.asList(100.0, 200.0));
        savingCapacity.setWithdrawalHistory(Arrays.asList(50.0));
        savingCapacity.setDepositHistory(Arrays.asList(300.0));

        // Stub de los métodos que se llaman en calculateCredit
        when(creditServicesMock.feeIncome(credit)).thenReturn(credit);
        when(creditServicesMock.creditHistory(credit)).thenReturn(credit);
        when(creditServicesMock.WorkSituacion(credit)).thenReturn(credit);
        when(creditServicesMock.Debs(credit)).thenReturn(credit);
        when(creditServicesMock.financingCredit(credit)).thenReturn(credit);
        when(creditServicesMock.Verifyyears(credit)).thenReturn(credit);
        when(scServices.searchSavingCapacity(1L)).thenReturn(savingCapacity);
        when(scServices.calculateSavingHistory(savingCapacity)).thenReturn(true);
        when(scServices.calculateDepositHistory(savingCapacity, credit.getIngress())).thenReturn(true);
        when(scServices.calculateWithdrawals(savingCapacity)).thenReturn(true);

        creditServices.calculateCredit(credit);

        assertThat(credit.getAprovedEmployed()).isEqualTo(1);
    }

    @Test
    void CalculateCost() {
        // Crea el objeto Credit
        Credit credit = new Credit();
        credit.setCuota(5.0);
        credit.setIngress(500.0);
        credit.setAmount(1000.0);
        credit.setTerm(10);

        Credit result = creditServices.CalculateCost(credit);
        assertThat(result.getCostT()).isEqualTo(2400790.0); // Cambia a 1, si esperas que sea así
    }

    @Test
    void evaluateCredit(){
        Credit credit = new Credit();
        credit.setCuota(5.0);
        credit.setIngress(500.0);
        credit.setAmount(1000.0);
        credit.setId_savingCapacity(1L);
        credit.setId_user(1L);
        credit.setAmountDebs(0.0);
        credit.setStatusDicom(-1);
        credit.setAprovedDebs(1);
        credit.setAprovedFeeIncome(1);
        credit.setYears(25);
        credit.setTerm(12);
        credit.setAprovedEmployed(1);
        credit.setAprovedYears(1);
        credit.setAprovedSavingCapacity(1);
        credit.setCuota(5.0);
        credit.setIngress(500.0);
        credit.setAmount(1000.0);
        credit.setTerm(10);

        when(creditServicesMock.CalculateCost(credit)).thenReturn(credit);

        creditServices.evaluateCredit(credit);

        assertThat(credit.getAprovedApplication()).isEqualTo(0); // Cambia a 1, si esperas que sea así
    }

}
