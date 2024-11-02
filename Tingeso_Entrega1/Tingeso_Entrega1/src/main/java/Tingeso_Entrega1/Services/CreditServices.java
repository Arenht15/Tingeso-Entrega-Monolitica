package Tingeso_Entrega1.Services;

import Tingeso_Entrega1.Entities.Credit;
import Tingeso_Entrega1.Entities.SavingCapacity;
import Tingeso_Entrega1.Repositories.CreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.function.IntPredicate;

@Service
public class CreditServices {
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private SavingCapacityServices savingCapacityServices;

    public List<Credit> getCredits(){
        return creditRepository.findCredits();
    }
    public Credit saveCredit(Credit c) {
        creditRepository.save(c);
        return c;
    }
    public Credit searchCredit(Long id){
        try {
            return creditRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public List<Credit> findCreditsByUserId(Long userId){
        return creditRepository.findCreditsByUserId(userId);
    }

    public Credit feeIncome(Credit c) {
        double numerator = c.getCuota() / c.getIngress();
        double feeIncome = numerator * 100;
        if (feeIncome > 0.0 && feeIncome < 35.0) {
            c.setAprovedFeeIncome(1);
        } else {
            c.setAprovedFeeIncome(0);
        }
        return c;
    }

    public Credit creditHistory(Credit c) {
        if (c.getStatusDicom() == 0) {
            c.setStatusDicom(0);
        } else if (c.getStatusDicom() == 1) {
            c.setStatusDicom(1);
        }
        return c;
    }


    public Credit WorkSituacion(Credit c){
        if(c.getTypeJob() == 0){
            if(c.getSeniority() < 1){
                c.setAprovedEmployed(0);
            }else{
                c.setAprovedEmployed(1);
            }
        } else{
            Double ingressAcum = c.getIngressAcum();
            if(ingressAcum < 6000000.0){
                c.setAprovedEmployed(0);
            }else{
                c.setAprovedEmployed(1);
            }
        }
        return c;

    }

    public Credit Debs(Credit c) {
        if (c.getAmountDebs() == 0.0) {
            c.setAprovedDebs(1);
        }else {
            double midIngres = c.getIngress() * 0.5;
            double deb = c.getAmountDebs() + c.getCuota();
            if (deb < midIngres) {
                c.setAprovedDebs(1);
            } else {
                c.setAprovedDebs(0);
            }
        }
        return c;
    }

    public Credit financingCredit(Credit c) {
        Double porcent = c.getPorcent()/100;
        if(porcent <= 0.0 || porcent > 0.8){
            c.setAmountApproved(0);
        }else{
            Integer type = c.getType();
            if(type == 4){
              if(porcent <= 0.5){
                    c.setAmountApproved(1);
              }else{
                  c.setAmountApproved(0);
              }
            }else if (type == 3) {
                if (porcent <= 0.6) {
                    c.setAmountApproved(1);
                }else{
                    c.setAmountApproved(0);
                }
            }else if(type == 2) {
                if (porcent <= 0.7) {
                    c.setAmountApproved(1);
                }else{
                    c.setAmountApproved(0);
                }
            }else if(type == 1) {
                if (porcent <= 0.8) {
                    c.setAmountApproved(1);
                }else{
                    c.setAmountApproved(0);
                }
            }
        }
        return c;
    }

    public Credit Verifyyears(Credit c) {
        Integer years = c.getYears();
        if (years < 18) {
            c.setAprovedYears(0);
        } else {
            double term = c.getTerm();
            double y = years + term + 5;
            if (y < 75) {
                c.setAprovedYears(1);
            } else {
                c.setAprovedYears(0);
            }
        }
        return c;
    }
    public Integer calculateYears(LocalDate birthdate){
        LocalDate now = LocalDate.now();
        Integer years = now.getYear() - birthdate.getYear();
        return years;
    }


    public Credit SavingCapacity(Credit c) {
        SavingCapacity sc = savingCapacityServices.searchSavingCapacity(c.getId_savingCapacity());
        int ptj = 5;
        Double tenProcent = c.getAmount() * 0.1;
        if(sc.getScAmount() > tenProcent){
            ptj = ptj - 1;
        }
        if(savingCapacityServices.calculateSavingHistory(sc)){
            ptj = ptj - 1;
        }if(savingCapacityServices.calculateDepositHistory(sc, c.getIngress())){
            ptj = ptj - 1;
        }if(savingCapacityServices.calculateWithdrawals(sc)){
            ptj = ptj - 1;
        }
        if(sc.getSavingYears() <2){
            if(sc.getSavingAmountAcum() < c.getAmount()*0.2){
                ptj = ptj - 1;
            }
        }else{
            if(sc.getSavingAmountAcum() < c.getAmount()*0.1){
                ptj = ptj - 1;
            }
        }
        if(ptj < 4){
            c.setAprovedSavingCapacity(0);
        }else{
            c.setAprovedSavingCapacity(1);
        }
        return c;
    }

    public void calculateCredit(Credit c) {
        c = feeIncome(c); // feeIncome evaluation
        c = creditHistory(c); // credit history evaluation
        c = WorkSituacion(c); // work situation evaluation
        c = Debs(c); // debs evaluation
        c = financingCredit(c); // financing credit evaluation
        c = Verifyyears(c); // years old evaluation
        c = SavingCapacity(c); // saving capacity evaluation
        evaluateCredit(c); // evaluate credit
    }

    public void evaluateCredit(Credit c) {
        if (c.getAprovedFeeIncome() == 1
                && c.getStatusDicom() == 1
                && c.getAprovedDebs() == 1
                && c.getAmountApproved() == 1
                && c.getAprovedYears() == 1
                && c.getAprovedSavingCapacity() == 1) {
            Credit Final = CalculateCost(c);
            Final.setAprovedApplication(1);
            creditRepository.save(Final);

        } else {
            c.setAprovedApplication(0);
            creditRepository.save(c);
        }
    }

    public Credit CalculateCost(Credit c){
        double cost = c.getCuota()*0.3 + 20000; // fire and life insurance
        c.setCreditLifeInsurance(cost);
        double costM = cost + c.getCuota(); // month cost
        double Commission = c.getAmount()*0.01;
        c.setCreditJob(Commission);
        double costT = costM*(c.getTerm()*12) + Commission; // total cost
        c.setCostM(costM);
        c.setCostT(costT);
        return c;
    }

}