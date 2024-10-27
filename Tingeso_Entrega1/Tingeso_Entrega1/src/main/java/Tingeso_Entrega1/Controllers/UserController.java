package Tingeso_Entrega1.Controllers;

import Tingeso_Entrega1.Entities.User;
import Tingeso_Entrega1.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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

        // Crear un nuevo usuario con los datos recibidos
        User user = new User();
        user.setRut(rut);
        user.setEmail(email);
        user.setName(name);
        user.setSurname(surname);
        user.setBirthdate(LocalDate.parse(birthdate));
        // File -> byte[]
        try {
            user.setIdentification(identification.getBytes());
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(null);
        }
        return ResponseEntity.ok(userServices.saveUser(user));
    }

    @GetMapping("/simulateCredit/{type}/{amount}/{term}/{rate}")
    public ResponseEntity<Double> simulateCredit(@PathVariable Integer type, @PathVariable Double amount,
                                               @PathVariable Integer term, @PathVariable Double rate){
        Double simulation = userServices.simulateCredit(type, amount, term, rate);
        return ResponseEntity.ok(simulation);
    }
    @GetMapping("/SearchUser")
    public ResponseEntity<Boolean> searchUser(@RequestParam String rut){
        Boolean bandera = userServices.searchUser(rut);
        return ResponseEntity.ok(bandera);
    }

    @PostMapping(value = "/saveUser")
    public User saveUser(@RequestBody User user){
        return userServices.saveUser(user);
    }

    @PutMapping("/updateUser")
    public User updateUser(@RequestBody User user){
        return userServices.updateUser(user);
    }
}
