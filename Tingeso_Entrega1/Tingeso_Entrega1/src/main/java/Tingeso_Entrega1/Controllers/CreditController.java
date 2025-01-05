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

        Credit c = creditServices.save(id_user, type, amount,
                term, rate, cuota, birthday, porcentaje, rut);
        return ResponseEntity.ok(c);
    }
    @PutMapping("/Part1")
    public ResponseEntity<Credit> updateSolicitud(
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
            @RequestParam("AhorroFile") MultipartFile AhorroFile,
            @RequestParam("TipoEmpleo") Integer TipoEmpleo,
            @RequestParam("idSc") Long idSc){
        Credit bandera = creditServices.updateSolicitud(id, ingress, statusDicom,
                Seniority, IngressAcum, deudas, LaboralFile, dicomFile,
                ingressFile, deudasFile, AhorroFile, TipoEmpleo, idSc);
        return ResponseEntity.ok(bandera);
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

         creditServices.actualizaSolicitud(id, ingress, statusDicom, Seniority, ingressAcum, deudas, years, TipoEmpleo);
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
        return ResponseEntity.ok(creditServices.updateStatus(id));
    }

    @GetMapping("/getCreditByUserId/{userId}")
    public ResponseEntity<List<Credit>> getCreditByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(creditServices.findCreditsByUserId(userId));
    }
}