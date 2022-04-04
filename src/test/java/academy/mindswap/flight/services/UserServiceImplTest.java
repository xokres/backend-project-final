package academy.mindswap.flight.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import academy.mindswap.flight.commands.InsertUserDto;
import academy.mindswap.flight.commands.UserDto;
import academy.mindswap.flight.converters.UserConverter;
import academy.mindswap.flight.exceptions.InvalidUserId;
import academy.mindswap.flight.exceptions.UserNotFoundException;
import academy.mindswap.flight.persistence.models.Flight;
import academy.mindswap.flight.persistence.models.Role;
import academy.mindswap.flight.persistence.models.User;
import academy.mindswap.flight.persistence.repositories.RoleRepository;
import academy.mindswap.flight.persistence.repositories.UserRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {UserServiceImpl.class, BCryptPasswordEncoder.class, RoleService.class,
        UserConverter.class, ModelMapper.class})
@ExtendWith(SpringExtension.class)
class UserServiceImplTest {
    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    void testLoadUserByUsername() throws UsernameNotFoundException {
        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("teste");
        user.setRoles(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findByEmail((String) any())).thenReturn(ofResult);

        // Act
        UserDetails actualLoadUserByUsernameResult = this.userServiceImpl.loadUserByUsername("jane.doe@example.org");

        // Assert
        assertTrue(actualLoadUserByUsernameResult.getAuthorities().isEmpty());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals("teste", actualLoadUserByUsernameResult.getPassword());
        verify(this.userRepository).findByEmail((String) any());
    }

   

    @Test
    void testLoadUserByUsername2() throws UsernameNotFoundException {
        // Arrange
        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);

        HashSet<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getPassword()).thenReturn("teste");
        when(user.getRoles()).thenReturn(roleSet);
        doNothing().when(user).setAge((Integer) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFlights((Set<Flight>) any());
        doNothing().when(user).setIdNumber((Long) any());
        doNothing().when(user).setIdentificationNumber((Integer) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRoles((Set<Role>) any());
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("teste");
        user.setRoles(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findByEmail((String) any())).thenReturn(ofResult);

        // Act
        UserDetails actualLoadUserByUsernameResult = this.userServiceImpl.loadUserByUsername("jane.doe@example.org");

        // Assert
        assertEquals(1, actualLoadUserByUsernameResult.getAuthorities().size());
        assertTrue(actualLoadUserByUsernameResult.isEnabled());
        assertTrue(actualLoadUserByUsernameResult.isCredentialsNonExpired());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonLocked());
        assertTrue(actualLoadUserByUsernameResult.isAccountNonExpired());
        assertEquals("jane.doe@example.org", actualLoadUserByUsernameResult.getUsername());
        assertEquals("teste", actualLoadUserByUsernameResult.getPassword());
        verify(this.userRepository).findByEmail((String) any());
        verify(user).getEmail();
        verify(user).getPassword();
        verify(user).getRoles();
        verify(user).setAge((Integer) any());
        verify(user).setEmail((String) any());
        verify(user).setFlights((Set<Flight>) any());
        verify(user).setIdNumber((Long) any());
        verify(user).setIdentificationNumber((Integer) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRoles((Set<Role>) any());
    }

    @Test
    void testLoadUserByUsername3() throws UsernameNotFoundException {
        // Arrange
        when(this.userRepository.findByEmail((String) any())).thenReturn(Optional.empty());
        User user = mock(User.class);
        when(user.getEmail()).thenReturn("jane.doe@example.org");
        when(user.getPassword()).thenReturn("teste");
        when(user.getRoles()).thenReturn(new HashSet<>());
        doNothing().when(user).setAge((Integer) any());
        doNothing().when(user).setEmail((String) any());
        doNothing().when(user).setFlights((java.util.Set<Flight>) any());
        doNothing().when(user).setIdNumber((Long) any());
        doNothing().when(user).setIdentificationNumber((Integer) any());
        doNothing().when(user).setName((String) any());
        doNothing().when(user).setPassword((String) any());
        doNothing().when(user).setRoles((java.util.Set<Role>) any());
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("teste");
        user.setRoles(new HashSet<>());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> this.userServiceImpl.loadUserByUsername("jane.doe@example.org"));
        verify(this.userRepository).findByEmail((String) any());
        verify(user).setAge((Integer) any());
        verify(user).setEmail((String) any());
        verify(user).setFlights((java.util.Set<Flight>) any());
        verify(user).setIdNumber((Long) any());
        verify(user).setIdentificationNumber((Integer) any());
        verify(user).setName((String) any());
        verify(user).setPassword((String) any());
        verify(user).setRoles((java.util.Set<Role>) any());
    }

    @Test
    void testSave() {
        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("teste");
        user.setRoles(new HashSet<>());
        when(this.userRepository.save((User) any())).thenReturn(user);

        // Act
        UserDto actualSaveResult = this.userServiceImpl.save(new UserDto());

        // Assert
        assertEquals(1, actualSaveResult.getAge().intValue());
        assertTrue(actualSaveResult.getRoles().isEmpty());
        assertEquals("Name", actualSaveResult.getName());
        assertEquals(1L, actualSaveResult.getIdNumber().longValue());
        assertTrue(actualSaveResult.getFlights().isEmpty());
        assertEquals("jane.doe@example.org", actualSaveResult.getEmail());
        verify(this.userRepository).save((User) any());
    }

   
    @Test
    void testSave2() {
        // Arrange
        when(this.userRepository.save((User) any())).thenThrow(new InvalidUserId("42"));

        // Act and Assert
        assertThrows(InvalidUserId.class, () -> this.userServiceImpl.save(new UserDto()));
        verify(this.userRepository).save((User) any());
    }

   

    @Test
    void testRegister() {
        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("teste");
        user.setRoles(new HashSet<>());
        when(this.userRepository.save((User) any())).thenReturn(user);

        Role role = new Role();
        role.setName("Name");
        role.setRoleId(123L);
        when(this.roleRepository.findByName((String) any())).thenReturn(role);

        InsertUserDto insertUserDto = new InsertUserDto();
        insertUserDto.setPassword("teste");

        // Act
        UserDto actualRegisterResult = this.userServiceImpl.register(insertUserDto);

        // Assert
        assertEquals(1, actualRegisterResult.getAge().intValue());
        assertTrue(actualRegisterResult.getRoles().isEmpty());
        assertEquals("Name", actualRegisterResult.getName());
        assertEquals(1L, actualRegisterResult.getIdNumber().longValue());
        assertTrue(actualRegisterResult.getFlights().isEmpty());
        assertEquals("jane.doe@example.org", actualRegisterResult.getEmail());
        verify(this.userRepository).save((User) any());
        verify(this.roleRepository).findByName((String) any());
    }
    
    @Test
    void testFindAll() {
       

        // Arrange
        when(this.userRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(this.userServiceImpl.findAll().isEmpty());
        verify(this.userRepository).findAll();
    }

    @Test
    void testFindAll2() {
        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("teste");
        user.setRoles(new HashSet<>());

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user);
        when(this.userRepository.findAll()).thenReturn(userList);

        // Act and Assert
        assertEquals(1, this.userServiceImpl.findAll().size());
        verify(this.userRepository).findAll();
    }

    @Test
    void testFindAll3() {
        // Arrange
        when(this.userRepository.findAll()).thenThrow(new InvalidUserId("42"));

        // Act and Assert
        assertThrows(InvalidUserId.class, () -> this.userServiceImpl.findAll());
        verify(this.userRepository).findAll();
    }

    @Test
    void testFindAll4() {
        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("teste");
        user.setRoles(new HashSet<>());

        User user1 = new User();
        user1.setAge(1);
        user1.setEmail("jane.doe@example.org");
        user1.setFlights(new HashSet<>());
        user1.setIdNumber(1L);
        user1.setIdentificationNumber(1);
        user1.setName("Name");
        user1.setPassword("teste");
        user1.setRoles(new HashSet<>());

        ArrayList<User> userList = new ArrayList<>();
        userList.add(user1);
        userList.add(user);
        when(this.userRepository.findAll()).thenReturn(userList);

        // Act and Assert
        assertEquals(2, this.userServiceImpl.findAll().size());
        verify(this.userRepository).findAll();
    }

    @Test
    void testFindOne() {
        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("teste");
        user.setRoles(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findByEmail((String) any())).thenReturn(ofResult);

        // Act
        UserDto actualFindOneResult = this.userServiceImpl.findOne("jane.doe@example.org");

        // Assert
        assertEquals(1, actualFindOneResult.getAge().intValue());
        assertTrue(actualFindOneResult.getRoles().isEmpty());
        assertEquals("Name", actualFindOneResult.getName());
        assertEquals(1L, actualFindOneResult.getIdNumber().longValue());
        assertTrue(actualFindOneResult.getFlights().isEmpty());
        assertEquals("jane.doe@example.org", actualFindOneResult.getEmail());
        verify(this.userRepository).findByEmail((String) any());
    }

    @Test
    void testFindOne2() {
        // Arrange
        when(this.userRepository.findByEmail((String) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(UserNotFoundException.class, () -> this.userServiceImpl.findOne("jane.doe@example.org"));
        verify(this.userRepository).findByEmail((String) any());
    }

    @Test
    void testFindOne3() {
        // Arrange
        when(this.userRepository.findByEmail((String) any())).thenThrow(new InvalidUserId("42"));

        // Act and Assert
        assertThrows(InvalidUserId.class, () -> this.userServiceImpl.findOne("jane.doe@example.org"));
        verify(this.userRepository).findByEmail((String) any());
    }

    @Test
    void testFindById() throws InvalidUserId {
        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("teste");
        user.setRoles(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        // Act
        UserDto actualFindByIdResult = this.userServiceImpl.findById(123L);

        // Assert
        assertEquals(1, actualFindByIdResult.getAge().intValue());
        assertTrue(actualFindByIdResult.getRoles().isEmpty());
        assertEquals("Name", actualFindByIdResult.getName());
        assertEquals(1L, actualFindByIdResult.getIdNumber().longValue());
        assertTrue(actualFindByIdResult.getFlights().isEmpty());
        assertEquals("jane.doe@example.org", actualFindByIdResult.getEmail());
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testFindById2() throws InvalidUserId {
        // Arrange
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(InvalidUserId.class, () -> this.userServiceImpl.findById(123L));
        verify(this.userRepository).findById((Long) any());
    }

    @Test
    void testFindById3() throws InvalidUserId {
        // Arrange
        when(this.userRepository.findById((Long) any())).thenThrow(new InvalidUserId("42"));

        // Act and Assert
        assertThrows(InvalidUserId.class, () -> this.userServiceImpl.findById(123L));
        verify(this.userRepository).findById((Long) any());
    }
}

