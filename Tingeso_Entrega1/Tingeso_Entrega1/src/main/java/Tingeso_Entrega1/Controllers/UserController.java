package Tingeso_Entrega1.Controllers;

import Tingeso_Entrega1.Entities.User;
import Tingeso_Entrega1.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.nio.charset.StandardCharsets;
import java.io.IOException;
import java.time.LocalDate;

import static org.springframework.http.RequestEntity.*;

@RestController
@RequestMapping("/prestabanco/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserServices userServices;

    @PostMapping("/save")
    public ResponseEntity<User> save(
            @RequestParam("rut") String rut,
            @RequestParam("email") String email,
            @RequestParam("name") String name,
            @RequestParam("surname") String surname,
            @RequestParam("birthdate") String birthdate,
            @RequestParam("identification") MultipartFile identification) {

        User user = userServices.saveUser(rut, email, name, surname, birthdate, identification);
        return ResponseEntity.ok(user);
    }

    @GetMapping("/simulateCredit/{type}/{amount}/{term}/{rate}")
    public ResponseEntity<Double> simulateCredit(@PathVariable Integer type, @PathVariable Double amount,
                                               @PathVariable Integer term, @PathVariable Double rate){
        Double simulation = userServices.simulateCredit(type, amount, term, rate);
        return ResponseEntity.ok(simulation);
    }
    @GetMapping("/SearchUser")
    public ResponseEntity<User> searchUser(@RequestParam String rut){
        User bandera = userServices.searchUser(rut);
        return ResponseEntity.ok(bandera);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return userServices.updateUser(user);
    }
}
