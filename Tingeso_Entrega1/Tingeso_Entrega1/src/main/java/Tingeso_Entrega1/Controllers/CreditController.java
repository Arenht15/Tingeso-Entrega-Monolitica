package Tingeso_Entrega1.Controllers;
import Tingeso_Entrega1.Entities.Credit;
import Tingeso_Entrega1.Entities.SavingCapacity;
import Tingeso_Entrega1.Entities.User;
import Tingeso_Entrega1.Services.CreditServices;
import Tingeso_Entrega1.Services.SavingCapacityServices;
import Tingeso_Entrega1.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.RequestEntity.*;

@RestController
@RequestMapping("/prestabanco/credit")
@CrossOrigin(origins = "*")
public class CreditController {
    @Autowired
    private CreditServices creditServices;
    @Autowired
    private SavingCapacityServices savingCapacityServices;
    @Autowired
    private UserServices userServices;

    @PostMapping("/save")
    public ResponseEntity<Credit> save(
            @RequestParam("id_user") Long id_user,
            @RequestParam("type") Integer type,
            @RequestParam("amount") Double amount,
            @RequestParam("term") Integer term,
            @RequestParam("rate") Double rate,
            @RequestParam("cuota") Double cuota,
            @RequestParam("birthdate") String birthday,
            @RequestParam("Porcentaje") Double porcentaje,
            @RequestParam("rut") String rut){

        User user = userServices.searchUser(rut);
        Credit credit = new Credit();
        credit.setId_user(id_user);
        credit.setRut(rut);
        credit.setType(type);
        credit.setAmount(amount);
        credit.setTerm(term);
        credit.setRate(rate);
        credit.setCuota(cuota);
        credit.setYears(creditServices.calculateYears(LocalDate.parse(birthday)));
        credit.setIdentidadFile(user.getIdentification());
        credit.setPorcent(porcentaje);
        credit = creditServices.saveCredit(credit);
        return ResponseEntity.ok(credit);
    }

    @PutMapping("/Part1")
    public ResponseEntity<Credit> Part1(
            @RequestParam("id") Long id,
            @RequestParam("Ingress") Double ingress,
            @RequestParam("statusDicom") Integer statusDicom,
            @RequestParam("seniority") Integer Seniority,
            @RequestParam("IngressAcum") Double IngressAcum,
            @RequestParam("amountDebs") Double deudas,
            @RequestParam("payFile") MultipartFile LaboralFile,
            @RequestParam("histDicom") MultipartFile dicomFile,
            @RequestParam("ingressFile") MultipartFile ingressFile,
            @RequestParam("debs") MultipartFile deudasFile,
            @RequestParam("MontoActual") Double amountSc,
            @RequestParam("MontoAcumulado") Double amountAcum,
            @RequestParam("Antiguedad") Integer Antiguedad,
            @RequestParam("MontoAhorro") List<Double> ahorro,
            @RequestParam("AbonoAhorro") List<Double> abono,
            @RequestParam("RetiroAhorro") List<Double> retiro,
            @RequestParam("AhorroFile") MultipartFile AhorroFile,
            @RequestParam("TipoEmpleo") Integer TipoEmpleo){

        // Create a new SavingCapacity object
        SavingCapacity sc = new SavingCapacity();
        sc.setScAmount(amountSc);
        sc.setSavingAmountAcum(amountAcum);
        sc.setSavingYears(Antiguedad);
        sc.setSavingHistory(ahorro);
        sc.setDepositHistory(abono);
        sc.setWithdrawalHistory(retiro);
        SavingCapacity sc2 = savingCapacityServices.saveSavingCapacity(sc);

        // Search the credit by id, add the SavingCapacity object and other data
        Credit credit = creditServices.searchCredit(id);
        credit.setIngress(ingress);
        credit.setStatusDicom(statusDicom);
        credit.setSeniority(Seniority);
        credit.setIngressAcum(IngressAcum);
        credit.setAmountDebs(deudas);
        credit.setId_savingCapacity(sc2.getId());
        credit.setTypeJob(TipoEmpleo);
        credit.setAprovedApplication(-1);

        try {
            credit.setPayFile(LaboralFile.getBytes());
            credit.setHistDicom(dicomFile.getBytes());
            credit.setIngressFile(ingressFile.getBytes());
            credit.setDebs(deudasFile.getBytes());
            credit.setSavingCapacityFile(AhorroFile.getBytes());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
        return ResponseEntity.ok(creditServices.saveCredit(credit));
    }

    @PutMapping("/EvaluarCredit")
    public void EvaluarCredit(@RequestParam("id") Long id,
                              @RequestParam("Ingress") Double ingress,
            @RequestParam("statusDicom") Integer statusDicom,
            @RequestParam("seniority") Integer Seniority,
            @RequestParam("ingressAcum") Double ingressAcum,
            @RequestParam("amountDebs") Double deudas,
            @RequestParam("years") Integer years,
            @RequestParam("typejob") Integer TipoEmpleo){

        Credit credit = creditServices.searchCredit(id);
        credit.setIngress(ingress);
        credit.setStatusDicom(statusDicom);
        credit.setSeniority(Seniority);
        credit.setIngressAcum(ingressAcum);
        credit.setAmountDebs(deudas);
        credit.setTypeJob(TipoEmpleo);
        credit.setYears(years);
        creditServices.calculateCredit(credit);
    }

    @GetMapping("/getCredits")
    public ResponseEntity<List<Credit>> getCredits(){
        return ResponseEntity.ok(creditServices.getCredits());
    }

    @GetMapping("/getCredit/{id}")
    public ResponseEntity<Credit> getCredit(@PathVariable Long id){
        Credit credit = creditServices.searchCredit(id);
        return ResponseEntity.ok(credit);
    }

    @PutMapping("/updateStatus/{id}")
    public ResponseEntity<Credit> updateCredit(@PathVariable Long id){
        Credit credit = creditServices.searchCredit(id);
        credit.setAprovedApplication(2);
        return ResponseEntity.ok(creditServices.saveCredit(credit));
    }

    @GetMapping("/getCreditByUserId/{userId}")
    public ResponseEntity<List<Credit>> getCreditByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(creditServices.findCreditsByUserId(userId));
    }
}