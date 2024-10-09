package Tingeso_Entrega1.Controllers;

import Tingeso_Entrega1.Entities.User;
import Tingeso_Entrega1.Services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prestabanco/user")
@CrossOrigin(origins = "*")
public class UserController {
    @Autowired
    private UserServices userServices;

    @GetMapping("/simulateCredit/{type}/{amount}/{term}/{rate}")
    public Double simulateCredit(@PathVariable Integer type, @PathVariable Double amount,
                                 @PathVariable Integer term, @PathVariable Double rate){
        return userServices.simulateCredit(type, amount, term, rate);
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
