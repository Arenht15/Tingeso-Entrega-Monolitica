package Tingeso_Entrega1.Services;

import Tingeso_Entrega1.Entities.SavingCapacity;
import Tingeso_Entrega1.Repositories.SavingCapacityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SavingCapacityServices {
    @Autowired
    private SavingCapacityRepository savingCapacityRepository;

    public SavingCapacity save(Double scAmount, Integer Antiguedad, Double amountAcum,
                               List<Double> ahorro, List<Double> abono, List<Double> retiro){
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(scAmount);
        sc.setSavingAmountAcum(amountAcum);
        sc.setSavingYears(Antiguedad);
        sc.setSavingHistory(ahorro);
        sc.setDepositHistory(abono);
        sc.setWithdrawalHistory(retiro);
        try {
            savingCapacityRepository.save(sc);
            return sc;
        } catch (Exception e) {
            return null;
        }

    }

    public SavingCapacity saveSavingCapacity(SavingCapacity sc){
        return savingCapacityRepository.save(sc);
    }
    public SavingCapacity searchSavingCapacity(Long id){
        try {
            return savingCapacityRepository.findById(id).get();
        } catch (Exception e) {
            return null;
        }
    }

    public Boolean calculateSavingHistory(SavingCapacity sc){
        for (int i = 0; i < sc.getWithdrawalHistory().size(); i++) {
            if(sc.getSavingHistory().get(i) > 0.0){
                if(sc.getWithdrawalHistory().get(i) > sc.getSavingHistory().get(i)*0.5){
                    return true;
                }
            }else{
                return true;
            }
        }
        return false;
    }


    public  Boolean calculateDepositHistory(SavingCapacity sc, Double ingresos){
        for (int i = 1; i < sc.getSavingHistory().size(); i++) {
            if(sc.getDepositHistory().get(i) < ingresos*0.05){
                return true;
            }
        }
        return false;
    }

    public Boolean calculateWithdrawals(SavingCapacity sc){
        List<Double> retiros = sc.getWithdrawalHistory();
        for (int i = retiros.size()-1; i >= 6; i--) {
            if(sc.getSavingHistory().get(i) > 0.0){
                if(retiros.get(i) > sc.getSavingHistory().get(i)*0.3){
                    return true;
                }
            }
        }return false;
    }
}