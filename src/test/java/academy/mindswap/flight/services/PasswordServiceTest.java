package academy.mindswap.flight.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import academy.mindswap.flight.commands.PasswordDto;
import academy.mindswap.flight.persistence.models.User;
import academy.mindswap.flight.persistence.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

//@ContextConfiguration(classes = {PasswordService.class})
//@ExtendWith(SpringExtension.class)
class PasswordServiceTest {
//    @Autowired
//    private PasswordService passwordService;

    @MockBean
    private UserRepository userRepository;



//    @Test
//    void testChangePassword() {
//        // Arrange, Act and Assert
//        assertFalse(
//                this.passwordService.changePassword(new PasswordDto(1, "iloveyou", "iloveyou", "New Password Confirm")));
//    }


//    @Test
//    void testChangePassword2() {
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("iloveyou");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        Optional<User> ofResult = Optional.of(user);
//
//        User user1 = new User();
//        user1.setAge(1);
//        user1.setEmail("jane.doe@example.org");
//        user1.setFlights(new HashSet<>());
//        user1.setIdNumber(1L);
//        user1.setIdentificationNumber(1);
//        user1.setName("Name");
//        user1.setPassword("iloveyou");
////        user1.setPermissions("Permissions");
//        user1.setRoles(new HashSet<>());
//        when(this.userRepository.save((User) any())).thenReturn(user1);
//        when(this.userRepository.findById(anyLong())).thenReturn(ofResult);
//
//        // Act and Assert
//        assertTrue(this.passwordService
//                .changePassword(new PasswordDto(1, "iloveyou", "New Password Confirm", "New Password Confirm")));
//        verify(this.userRepository).save((User) any());
//        verify(this.userRepository, atLeast(1)).findById(anyLong());
//    }
//
//    @Test
//    void testChangePassword3() {
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("New Password Confirm");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        Optional<User> ofResult = Optional.of(user);
//
//        User user1 = new User();
//        user1.setAge(1);
//        user1.setEmail("jane.doe@example.org");
//        user1.setFlights(new HashSet<>());
//        user1.setIdNumber(1L);
//        user1.setIdentificationNumber(1);
//        user1.setName("Name");
//        user1.setPassword("iloveyou");
////        user1.setPermissions("Permissions");
//        user1.setRoles(new HashSet<>());
//        when(this.userRepository.save((User) any())).thenReturn(user1);
//        when(this.userRepository.findById(anyLong())).thenReturn(ofResult);
//
//        // Act and Assert
//        assertFalse(this.passwordService
//                .changePassword(new PasswordDto(1, "iloveyou", "New Password Confirm", "New Password Confirm")));
//        verify(this.userRepository).findById(anyLong());
//    }
//
//



}

