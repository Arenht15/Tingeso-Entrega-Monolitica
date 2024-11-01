package Tingeso_Entrega1.Controllers;

import Tingeso_Entrega1.Entities.SavingCapacity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Tingeso_Entrega1.Services.SavingCapacityServices;

import java.util.List;

@RestController
@RequestMapping("/prestabanco/SavingCapacity")
@CrossOrigin(origins = "*")
public class SavingCapacityController {

    @Autowired
    private SavingCapacityServices savingCapacityServices;

    @GetMapping("/searchSavingCapacity/{id}")
    public ResponseEntity<SavingCapacity> searchSavingCapacity(@PathVariable Long id) {
        SavingCapacity bandera = savingCapacityServices.searchSavingCapacity(id);
        return ResponseEntity.ok(bandera);
    }

    @PutMapping("/update")
    public ResponseEntity<SavingCapacity> updateSavingCapacity(@RequestParam("id") Long id,
                                                               @RequestParam("scAmount") Double scAmount,
                                                               @RequestParam("savingYears") Integer savingYears,
                                                               @RequestParam("savingAmountAcum") Double savingAmountAcum,
                                                               @RequestParam("MontoAhorro") List<Double> savingHistory,
                                                               @RequestParam("AbonoAhorro") List<Double> depositHistory,
                                                               @RequestParam("RetiroAhorro") List<Double> withdrawalHistory) {
        SavingCapacity sc = savingCapacityServices.searchSavingCapacity(id);
        sc.setScAmount(scAmount);
        sc.setSavingYears(savingYears);
        sc.setSavingAmountAcum(savingAmountAcum);
        sc.setSavingHistory(savingHistory);
        sc.setDepositHistory(depositHistory);
        sc.setWithdrawalHistory(withdrawalHistory);
        return ResponseEntity.ok(savingCapacityServices.saveSavingCapacity(sc));
    }
}
