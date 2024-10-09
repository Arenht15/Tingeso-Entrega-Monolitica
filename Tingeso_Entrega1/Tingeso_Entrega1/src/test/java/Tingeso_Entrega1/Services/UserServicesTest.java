package Tingeso_Entrega1.Services;
import Tingeso_Entrega1.Entities.User;
import Tingeso_Entrega1.Repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.*;

public class UserServicesTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServices userServices;
    @InjectMocks
    private User user;
    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }
    @Test
    void testSimulateCredit(){
        Double result = userServices.simulateCredit(1, 1000000.0, 12, 4.0);
        assertThat(result).isEqualTo(8753.3416);
    }
    @Test
    void testSimulateCredit2(){
        Double result = userServices.simulateCredit(0, 1000000.0, 12, 4.0);
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    void testSimulateCredit3(){
        Double result = userServices.simulateCredit(1, -1000000.0, 12, 4.0);
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    void testSimulateCredit4(){
        Double result = userServices.simulateCredit(2, 1000000.0, 40, 3.5);
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    void testSimulateCredit5(){
        Double result = userServices.simulateCredit(2, 2000000.0, 12, 5.5);
        assertThat(result).isEqualTo(19003.4434);
    }

    @Test
    void testSimulateCredit6(){
        Double result = userServices.simulateCredit(3, 1000000.0, 5, 4.0);
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    void testSimulateCredit7(){
        Double result = userServices.simulateCredit(3, 1500000.0, 12, 5.5);
        assertThat(result).isEqualTo(14249.5251);
    }
    @Test
    void testSimulateCredit8(){
        Double result = userServices.simulateCredit(4, 1000000.0, 12, 4.0);
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    void testSimulateCredit9(){
        Double result = userServices.simulateCredit(4, 1000000.0, 12, 5.0);
        assertThat(result).isEqualTo(9248.9041);
    }

    @Test
    void testSimulateCredit10(){
        Double result = userServices.simulateCredit(1, 1000000.0, 9, 6.0);
        assertThat(result).isEqualTo(0.0);
    }


    @Test
    void testSimulateCredit11(){
        Double result = userServices.simulateCredit(1, 1000000.0, 12, 1.0);
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    void testSimulateCredit12(){
        Double result = userServices.simulateCredit(1, 1000000.0, 15, -4.0);
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    void testSimulateCredit13(){
        Double result = userServices.simulateCredit(1, 1000000.0, -12, 4.0);
        assertThat(result).isEqualTo(0.0);
    }

    @Test
    void testDeleteUser() throws Exception {
        User user = new User();
        user.setRut("12345678-9");
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        userServices.deleteUser(9L);
        assertThat(userRepository.findById(1L)).isNotEmpty();
    }

    @Test
    void testDeleteUser2() throws Exception {
        User user = new User();
        User user2 = new User();
        user.setRut("12345678-9");
        user2.setRut("22345678-1");
        userServices.deleteUser(user2.getId());
        assertThat(userRepository.findById(1L)).isEmpty();
    }

    @Test
    void testSaveUser(){
        User user = new User();
        user.setRut("12345678-9");
        when(userRepository.save(user)).thenReturn(user);
        User resultingUser = userServices.saveUser(user);
        assertThat(resultingUser.getRut()).isEqualTo("12345678-9");
    }

    @Test
    void testSaveUser1(){
        User user1 = new User();
        user1.setRut("12345678-9");
        User user2 = new User();
        user2.setRut("22345678-1");

        when(userRepository.save(user1)).thenReturn(user1);
        when(userRepository.save(user2)).thenReturn(user2);

        User result1 = userServices.saveUser(user1);
        User result2 = userServices.saveUser(user2);

        assertThat(result1.getRut()).isEqualTo("12345678-9");
        assertThat(result2.getRut()).isEqualTo("22345678-1");
    }

    @Test
    void testSaveUser2(){
        User user1 = new User();
        User user2 = new User();
        user.setRut("12345678-9");
        user.setRut("12345678-9");
        when(userRepository.save(user1)).thenReturn(user1);
        when(userRepository.save(user2)).thenThrow(new RuntimeException("Duplicate RUT"));
        User result1 = userServices.saveUser(user1);
        User result2 = null;
        try {
            result2 = userServices.saveUser(user2);
        } catch (Exception e) {
            // Expected exception for duplicate RUT
        }

        assertThat(result1).isNotNull();
        assertThat(result1.getRut()).isEqualTo("12345678-9");
        assertThat(result2).isNull();
    };
}
