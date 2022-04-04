package academy.mindswap.flight.services;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import academy.mindswap.flight.converters.UserConverter;
import academy.mindswap.flight.persistence.models.Role;
import academy.mindswap.flight.persistence.repositories.RoleRepository;
import academy.mindswap.flight.persistence.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {RoleService.class, UserConverter.class, ModelMapper.class})
@ExtendWith(SpringExtension.class)
class RoleServiceTest {
    @MockBean
    private UserRepository userRepository;

    @MockBean
    private RoleRepository roleRepository;

    @Autowired
    private RoleService roleService;

    @Test
    void testValidateRole() {


        // Arrange
        when(this.roleRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> this.roleService.validateRole("Role Name"));
        verify(this.roleRepository).findAll();
    }

    @Test
    void testValidateRole2() {


        // Arrange
        Role role = new Role();
        role.setName("Can't add random roles!");
        role.setRoleId(123L);

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role);
        when(this.roleRepository.findAll()).thenReturn(roleList);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> this.roleService.validateRole("Role Name"));
        verify(this.roleRepository).findAll();
    }

    @Test
    void testValidateRole3() {


        // Arrange
        when(this.roleRepository.findAll()).thenThrow(new IllegalArgumentException("Can't add random roles!"));

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> this.roleService.validateRole("Role Name"));
        verify(this.roleRepository).findAll();
    }

    @Test
    void testValidateRole4() {


        // Arrange
        Role role = new Role();
        role.setName("Can't add random roles!");
        role.setRoleId(123L);

        Role role1 = new Role();
        role1.setName("Can't add random roles!");
        role1.setRoleId(123L);

        ArrayList<Role> roleList = new ArrayList<>();
        roleList.add(role1);
        roleList.add(role);
        when(this.roleRepository.findAll()).thenReturn(roleList);

        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> this.roleService.validateRole("Role Name"));
        verify(this.roleRepository).findAll();
    }
}

