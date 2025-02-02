package Tingeso_Entrega1.Services;

import Tingeso_Entrega1.Entities.User;
import Tingeso_Entrega1.Repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Service
public class UserServices {
    @Autowired
    private UserRepository userRepository;

    public User getUser(Long id){
        return userRepository.findById(id).get();
    }

    public Double simulateCredit(Integer type, Double amount, Integer term, Double rate) {
        Double M = 0.0;
        if(amount <= 0 || term <= 0 || rate <= 0){
            return M;
        }
        if(type == 1){
            if(rate<3.5 || rate>5.0){
                return 0.0;
            }
            Double n = term*12.0;
            Double rAux = (rate/12.0)/100.0;
            Double r = Math.round(rAux * 100000.0) / 100000.0;
            Double x = r * Math.pow(1.0 + r, n);
            Double y = Math.pow(1.0 + r, n) - 1.0;
            M = amount*(x/y);
            M = Math.round(M * 10000.0) / 10000.0;
        } else if(type == 2){
            if(rate<4.0 || rate>6.0){
                return 0.0;
            }
            Double n = term*12.0;
            Double r = (rate/12.0)/100.0;
            Double x = r * Math.pow(1.0 + r, n);
            Double y = Math.pow(1.0 + r, n) - 1.0;
            M = amount*(x/y);
            M = Math.round(M * 10000.0) / 10000.0;
        } else if(type == 3){
            if(rate<5.0 || rate>7.0){
                return 0.0;
            }
            Double n = term*12.0;
            Double rAux = (rate/12.0)/100.0;
            Double r = Math.round(rAux * 100000.0) / 100000.0;
            Double x = r * Math.pow(1.0 + r, n);
            Double y = Math.pow(1.0 + r, n) - 1.0;
            M = amount*(x/y);
            M = Math.round(M * 10000.0) / 10000.0;
        } else if(type == 4){
            if(rate<4.5 || rate>6.0){
                return 0.0;
            }
            Double n = term*12.0;
            Double r = (rate/12.0)/100.0;
            Double x = r * Math.pow(1.0 + r, n);
            Double y = Math.pow(1.0 + r, n) - 1.0;
            M = amount*(x/y);
            M = Math.round(M * 10000.0) / 10000.0;
        }
        return M;
    }

    @Transactional
    public User searchUser(String rut) {
        try{
            User user = userRepository.findByRut(rut);
            return user;
        }catch (Exception e){
            return null;
        }
    }
    public List<User> getUsers(){
        return userRepository.findAll();
    }

    public User saveUser(String rut, String email, String name, String surname, String birthdate, MultipartFile identification) {
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
            return null;
        }
        try{
            user = userRepository.save(user);
            return user;
        }catch (Exception e){
            return null;
        }
    }

    public User updateUser(User user) {
        return userRepository.save(user);
    }
}


