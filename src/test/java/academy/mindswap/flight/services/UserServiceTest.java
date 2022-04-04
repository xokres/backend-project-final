package academy.mindswap.flight.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import academy.mindswap.flight.commands.BookFlightDto;
import academy.mindswap.flight.commands.InsertUserDto;
import academy.mindswap.flight.commands.UserDto;
import academy.mindswap.flight.converters.FlightConverter;
import academy.mindswap.flight.converters.UserConverter;
import academy.mindswap.flight.exceptions.InvalidUserId;
import academy.mindswap.flight.exceptions.RoleNotFoundException;
import academy.mindswap.flight.exceptions.UserNotFoundException;
import academy.mindswap.flight.persistence.models.Flight;
import academy.mindswap.flight.persistence.models.User;
import academy.mindswap.flight.persistence.repositories.FlightRepository;
import academy.mindswap.flight.persistence.repositories.RoleRepository;
import academy.mindswap.flight.persistence.repositories.UserRepository;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.Optional;
import java.util.function.BooleanSupplier;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class UserServiceTest {
    @MockBean
    private FlightRepository flightRepository;
//
//    @MockBean
//    private RoleRepository roleRepository;
//
//    @MockBean
//    private UserRepository userRepository;
//
//    @Autowired
//    private UserService userService;
//
//    @Test
//    void testGetUserByName() {
//
//        // Arrange
//        when(this.userRepository.findByName((String) any())).thenReturn(new ArrayList<>());
//
//        // Act and Assert
//        assertTrue(this.userService.findOne ("Name").isEmpty());
//        verify(this.userRepository).findByEmail((String) any());
//    }
//
//    @Test
//    void testGetUserByName2() {
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//
//        ArrayList<User> userList = new ArrayList<>();
//        userList.add(user);
//        when(this.userRepository.findByName((String) any())).thenReturn(userList);
//
//        // Act and Assert
//        assertEquals(1, this.userService.getUserByName("Name").size());
//        verify(this.userRepository).findByName((String) any());
//    }
//
//    @Test
//    void testGetUserByName3() {
//
//        // Arrange
//        when(this.userRepository.findByName((String) any())).thenThrow(new RoleNotFoundException("Rolename"));
//
//        // Act and Assert
//        assertThrows(RoleNotFoundException.class, () -> this.userService.getUserByName("Name"));
//        verify(this.userRepository).findByName((String) any());
//    }
//
//    @Test
//    @Disabled("TODO: Complete this test")
//    void testAddRoleToUser() {
//
//        // Arrange and Act
//        this.userService.addRoleToUser("jane.doe@example.org", "Role Name");
//    }
//
//
//    @Test
//    void testBookFlight() {
//
//
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
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
//        user1.setPassword("teste");
////        user1.setPermissions("Permissions");
//        user1.setRoles(new HashSet<>());
//        when(this.userRepository.save((User) any())).thenReturn(user1);
//        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
//
//        Flight flight = new Flight();
//        flight.setArrivalAirport("Arrival Airport");
//        flight.setArrivalDate("2020-03-01");
//        flight.setArrivalTime("Arrival Time");
//        flight.setDepartureAirport("Departure Airport");
//        flight.setDepartureDate("2020-03-01");
//        flight.setDepartureTime("Departure Time");
//        flight.setFlightNumber("42");
//        flight.setUsers(new HashSet<>());
//        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(flight);
//
//        BookFlightDto bookFlightDto = new BookFlightDto();
//        bookFlightDto.setFlightNumber("42");
//        bookFlightDto.setPassengerId(123L);
//
//        // Act
//        UserDto actualBookFlightResult = this.userService.bookFlight(bookFlightDto);
//
//        // Assert
//        assertEquals(1, actualBookFlightResult.getAge().intValue());
//        assertTrue(actualBookFlightResult.getRoles().isEmpty());
//        assertEquals("Name", actualBookFlightResult.getName());
//        assertEquals(1L, actualBookFlightResult.getIdNumber().longValue());
//        assertTrue(actualBookFlightResult.getFlights().isEmpty());
//        assertEquals("jane.doe@example.org", actualBookFlightResult.getEmail());
//        verify(this.userRepository).save((User) any());
//        verify(this.userRepository).findById((Long) any());
//        verify(this.flightRepository).findByFlightNumber((String) any());
//    }
//
//    @Test
//    void testBookFlight2() {
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        Optional<User> ofResult = Optional.of(user);
//        when(this.userRepository.save((User) any())).thenThrow(new RoleNotFoundException("Rolename"));
//        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
//
//        Flight flight = new Flight();
//        flight.setArrivalAirport("Arrival Airport");
//        flight.setArrivalDate("2020-03-01");
//        flight.setArrivalTime("Arrival Time");
//        flight.setDepartureAirport("Departure Airport");
//        flight.setDepartureDate("2020-03-01");
//        flight.setDepartureTime("Departure Time");
//        flight.setFlightNumber("42");
//        flight.setUsers(new HashSet<>());
//        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(flight);
//
//        BookFlightDto bookFlightDto = new BookFlightDto();
//        bookFlightDto.setFlightNumber("42");
//        bookFlightDto.setPassengerId(123L);
//
//        // Act and Assert
//        assertThrows(RoleNotFoundException.class, () -> this.userService.bookFlight(bookFlightDto));
//        verify(this.userRepository).save((User) any());
//        verify(this.userRepository).findById((Long) any());
//        verify(this.flightRepository).findByFlightNumber((String) any());
//    }
//
//
//    @Test
//    void testBookFlight3() {
//
//
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        when(this.userRepository.save((User) any())).thenReturn(user);
//        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());
//
//        Flight flight = new Flight();
//        flight.setArrivalAirport("Arrival Airport");
//        flight.setArrivalDate("2020-03-01");
//        flight.setArrivalTime("Arrival Time");
//        flight.setDepartureAirport("Departure Airport");
//        flight.setDepartureDate("2020-03-01");
//        flight.setDepartureTime("Departure Time");
//        flight.setFlightNumber("42");
//        flight.setUsers(new HashSet<>());
//        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(flight);
//
//        BookFlightDto bookFlightDto = new BookFlightDto();
//        bookFlightDto.setFlightNumber("42");
//        bookFlightDto.setPassengerId(123L);
//
//        // Act and Assert
//        assertNull(this.userService.bookFlight(bookFlightDto));
//        verify(this.userRepository).findById((Long) any());
//        verify(this.flightRepository).findByFlightNumber((String) any());
//    }
//
//    @Test
//    void testCancelFlight() {
//
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
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
//        user1.setPassword("teste");
////        user1.setPermissions("Permissions");
//        user1.setRoles(new HashSet<>());
//        when(this.userRepository.save((User) any())).thenReturn(user1);
//        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
//
//        Flight flight = new Flight();
//        flight.setArrivalAirport("Arrival Airport");
//        flight.setArrivalDate("2020-03-01");
//        flight.setArrivalTime("Arrival Time");
//        flight.setDepartureAirport("Departure Airport");
//        flight.setDepartureDate("2020-03-01");
//        flight.setDepartureTime("Departure Time");
//        flight.setFlightNumber("42");
//        flight.setUsers(new HashSet<>());
//        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(flight);
//
//        BookFlightDto bookFlightDto = new BookFlightDto();
//        bookFlightDto.setFlightNumber("42");
//        bookFlightDto.setPassengerId(123L);
//
//        // Act
//        UserDto actualCancelFlightResult = this.userService.cancelFlight(bookFlightDto);
//
//        // Assert
//        assertEquals(1, actualCancelFlightResult.getAge().intValue());
//        assertTrue(actualCancelFlightResult.getRoles().isEmpty());
//        assertEquals("Name", actualCancelFlightResult.getName());
//        assertEquals(1L, actualCancelFlightResult.getIdNumber().longValue());
//        assertTrue(actualCancelFlightResult.getFlights().isEmpty());
//        assertEquals("jane.doe@example.org", actualCancelFlightResult.getEmail());
//        verify(this.userRepository).save((User) any());
//        verify(this.userRepository).findById((Long) any());
//        verify(this.flightRepository).findByFlightNumber((String) any());
//    }
//
//    @Test
//    void testCancelFlight2() {
//
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        Optional<User> ofResult = Optional.of(user);
//        when(this.userRepository.save((User) any())).thenThrow(new RoleNotFoundException("Rolename"));
//        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
//
//        Flight flight = new Flight();
//        flight.setArrivalAirport("Arrival Airport");
//        flight.setArrivalDate("2020-03-01");
//        flight.setArrivalTime("Arrival Time");
//        flight.setDepartureAirport("Departure Airport");
//        flight.setDepartureDate("2020-03-01");
//        flight.setDepartureTime("Departure Time");
//        flight.setFlightNumber("42");
//        flight.setUsers(new HashSet<>());
//        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(flight);
//
//        BookFlightDto bookFlightDto = new BookFlightDto();
//        bookFlightDto.setFlightNumber("42");
//        bookFlightDto.setPassengerId(123L);
//
//        // Act and Assert
//        assertThrows(RoleNotFoundException.class, () -> this.userService.cancelFlight(bookFlightDto));
//        verify(this.userRepository).save((User) any());
//        verify(this.userRepository).findById((Long) any());
//        verify(this.flightRepository).findByFlightNumber((String) any());
//    }
//
//
//
////    @Test
////    void testFindById() {
////        // Arrange
////        User user = new User();
////        user.setAge(1);
////        user.setEmail("jane.doe@example.org");
////        user.setFlights(new HashSet<>());
////        user.setIdNumber(1L);
////        user.setIdentificationNumber(1);
////        user.setName("Name");
////        user.setPassword("teste");
//////        user.setPermissions("Permissions");
////        user.setRoles(new HashSet<>());
////        Optional<User> ofResult = Optional.of(user);
////        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);
////
////        // Act
////        UserDto actualFindByIdResult = this.userService.findById(123L);
////
////        // Assert
////        assertEquals(1, actualFindByIdResult.getAge().intValue());
////        assertTrue(actualFindByIdResult.getRoles().isEmpty());
////        assertEquals("Name", actualFindByIdResult.getName());
////        assertEquals(1L, actualFindByIdResult.getIdNumber().longValue());
////        assertTrue(actualFindByIdResult.getFlights().isEmpty());
////        assertEquals("jane.doe@example.org", actualFindByIdResult.getEmail());
////        verify(this.userRepository).findById((Long) any());
////    }
//
//
////    @Test
////    void testFindById2() {
////
////
////        // Arrange
////        when(this.userRepository.findById((Long) any())).thenThrow(new RoleNotFoundException("Rolename"));
////
////        // Act and Assert
////        assertThrows(RoleNotFoundException.class, () -> this.userService.findById(123L));
////        verify(this.userRepository).findById((Long) any());
////    }
//
//    @Test
//    void testSave() {
//
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        when(this.userRepository.save((User) any())).thenReturn(user);
//
//        // Act
//        UserDto actualSaveResult = this.userService.save(new InsertUserDto());
//
//        // Assert
//        assertEquals(1, actualSaveResult.getAge().intValue());
//        assertTrue(actualSaveResult.getRoles().isEmpty());
//        assertEquals("Name", actualSaveResult.getName());
//        assertEquals(1L, actualSaveResult.getIdNumber().longValue());
//        assertTrue(actualSaveResult.getFlights().isEmpty());
//        assertEquals("jane.doe@example.org", actualSaveResult.getEmail());
//        verify(this.userRepository).save((User) any());
//    }
//
//
//    @Test
//    void testSave2() {
//
//        // Arrange
//        when(this.userRepository.save((User) any())).thenThrow(new RoleNotFoundException("Rolename"));
//
//        // Act and Assert
//        assertThrows(RoleNotFoundException.class, () -> this.userService.save(new InsertUserDto()));
//        verify(this.userRepository).save((User) any());
//    }
//
//    @Test
//    void testSave3() {
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        when(this.userRepository.save((User) any())).thenReturn(user);
//
//        // Act
//        UserDto actualSaveResult = this.userService.save(new UserDto());
//
//        // Assert
//        assertEquals(1, actualSaveResult.getAge().intValue());
//        assertTrue(actualSaveResult.getRoles().isEmpty());
//        assertEquals("Name", actualSaveResult.getName());
//        assertEquals(1L, actualSaveResult.getIdNumber().longValue());
//        assertTrue(actualSaveResult.getFlights().isEmpty());
//        assertEquals("jane.doe@example.org", actualSaveResult.getEmail());
//        verify(this.userRepository).save((User) any());
//    }
//
//
//    @Test
//    void testSave4() {
//
//        // Arrange
//        when(this.userRepository.save((User) any())).thenThrow(new RoleNotFoundException("Rolename"));
//
//        // Act and Assert
//        assertThrows(RoleNotFoundException.class, () -> this.userService.save(new UserDto()));
//        verify(this.userRepository).save((User) any());
//    }
//
//    @Test
//    void testLogin() {
//
//
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        when(this.userRepository.findByNameAndPassword((String) any(), (String) any())).thenReturn(user);
//
//        // Act and Assert
//        assertSame(user, this.userService.login("Name", "teste"));
//        verify(this.userRepository).findByNameAndPassword((String) any(), (String) any());
//    }
//
//    @Test
//    void testLogin2() {
//
//
//        // Arrange
//        when(this.userRepository.findByNameAndPassword((String) any(), (String) any()))
//                .thenThrow(new RoleNotFoundException("Rolename"));
//
//        // Act and Assert
//        assertThrows(RoleNotFoundException.class, () -> this.userService.login("Name", "teste"));
//        verify(this.userRepository).findByNameAndPassword((String) any(), (String) any());
//    }
//
//    @Test
//    void testGetUserById() {
//
//
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        Optional<User> ofResult = Optional.of(user);
//        when(this.userRepository.findById(anyLong())).thenReturn(ofResult);
//
//        // Act
//        Optional<UserDto> actualUserById = this.userService.getUserById(123L);
//
//        // Assert
//        assertTrue(actualUserById.isPresent());
//        UserDto getResult = actualUserById.get();
//        assertEquals(1, getResult.getAge().intValue());
//        assertTrue(getResult.getRoles().isEmpty());
//        assertEquals("Name", getResult.getName());
//        assertEquals(1L, getResult.getIdNumber().longValue());
//        assertTrue(getResult.getFlights().isEmpty());
//        assertEquals("jane.doe@example.org", getResult.getEmail());
//        verify(this.userRepository).findById(anyLong());
//    }
//
//    @Test
//    void testGetUserById2() {
//
//
//        // Arrange
//        when(this.userRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        // Act and Assert
//        assertThrows(UserNotFoundException.class, () -> this.userService.getUserById(123L));
//        verify(this.userRepository).findById(anyLong());
//    }
//
//    @Test
//    void testGetUserById3() {
//
//
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        Optional<User> ofResult = Optional.of(user);
//        when(this.userRepository.findById(anyLong())).thenReturn(ofResult);
//
//        // Act and Assert
//        assertThrows(InvalidUserId.class, () -> this.userService.getUserById(-1L));
//    }
//
//    @Test
//    void testGetUserById4() {
//
//
//        // Arrange
//        when(this.userRepository.findById(anyLong()))
//                .thenThrow(new RoleNotFoundException("That particular user %s can't be found around here. Did he run away?"));
//
//        // Act and Assert
//        assertThrows(RoleNotFoundException.class, () -> this.userService.getUserById(123L));
//        verify(this.userRepository).findById(anyLong());
//    }
//
//    @Test
//    void testValidate() {
//
//
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        Optional<User> ofResult = Optional.of(user);
//        when(this.userRepository.findByEmail((String) any())).thenReturn(ofResult);
//
//        // Act and Assert
//        assertSame(user, this.userService.validate("jane.doe@example.org"));
//        verify(this.userRepository).findByEmail((String) any());
//    }
//
//    @Test
//    void testValidate2() {
//
//        // Arrange
//        when(this.userRepository.findByEmail((String) any())).thenReturn(Optional.empty());
//
//        // Act and Assert
//        assertThrows(UserNotFoundException.class, () -> this.userService.validate("jane.doe@example.org"));
//        verify(this.userRepository).findByEmail((String) any());
//    }
//
//    @Test
//    void testValidate3() {
//
//
//        // Arrange
//        when(this.userRepository.findByEmail((String) any()))
//                .thenThrow(new RoleNotFoundException("That particular user %s can't be found around here. Did he run away?"));
//
//        // Act and Assert
//        assertThrows(RoleNotFoundException.class, () -> this.userService.validate("jane.doe@example.org"));
//        verify(this.userRepository).findByEmail((String) any());
//    }
//
//    @Test
//    void testFindByEmailAndPassword() {
//
//
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        Optional<User> ofResult = Optional.of(user);
//        when(this.userRepository.findByEmailAndPassword((String) any(), (String) any())).thenReturn(ofResult);
//
//        // Act and Assert
//        assertSame(user, this.userService.findByEmailAndPassword("jane.doe@example.org", "teste"));
//        verify(this.userRepository).findByEmailAndPassword((String) any(), (String) any());
//    }
//
//    @Test
//    void testFindByEmailAndPassword2() {
//
//
//        // Arrange
//        when(this.userRepository.findByEmailAndPassword((String) any(), (String) any())).thenReturn(Optional.empty());
//
//        // Act and Assert
//        assertThrows(UserNotFoundException.class,
//                () -> this.userService.findByEmailAndPassword("jane.doe@example.org", "teste"));
//        verify(this.userRepository).findByEmailAndPassword((String) any(), (String) any());
//    }
//
//    @Test
//    void testFindByEmailAndPassword3() {
//
//
//        // Arrange
//        when(this.userRepository.findByEmailAndPassword((String) any(), (String) any()))
//                .thenThrow(new RoleNotFoundException("That particular user %s can't be found around here. Did he run away?"));
//
//        // Act and Assert
//        assertThrows(RoleNotFoundException.class,
//                () -> this.userService.findByEmailAndPassword("jane.doe@example.org", "teste"));
//        verify(this.userRepository).findByEmailAndPassword((String) any(), (String) any());
//    }
//
//    @Test
//    void testFindByEmail() {
//
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        Optional<User> ofResult = Optional.of(user);
//        when(this.userRepository.findByEmail((String) any())).thenReturn(ofResult);
//
//        // Act and Assert
//        assertSame(user, this.userService.findByEmail("jane.doe@example.org"));
//        verify(this.userRepository).findByEmail((String) any());
//    }
//
//    @Test
//    void testFindByEmail2() {
//
//
//        // Arrange
//        when(this.userRepository.findByEmail((String) any())).thenReturn(Optional.empty());
//
//        // Act and Assert
//        assertThrows(UserNotFoundException.class, () -> this.userService.findByEmail("jane.doe@example.org"));
//        verify(this.userRepository).findByEmail((String) any());
//    }
//
//    @Test
//    void testFindByEmail3() {
//
//        // Arrange
//        when(this.userRepository.findByEmail((String) any()))
//                .thenThrow(new RoleNotFoundException("That particular user %s can't be found around here. Did he run away?"));
//
//        // Act and Assert
//        assertThrows(RoleNotFoundException.class, () -> this.userService.findByEmail("jane.doe@example.org"));
//        verify(this.userRepository).findByEmail((String) any());
//    }
//
//    @Test
//    void testSignIn() {
//        // Arrange
//        User user = new User();
//        user.setAge(1);
//        user.setEmail("jane.doe@example.org");
//        user.setFlights(new HashSet<>());
//        user.setIdNumber(1L);
//        user.setIdentificationNumber(1);
//        user.setName("Name");
//        user.setPassword("teste");
////        user.setPermissions("Permissions");
//        user.setRoles(new HashSet<>());
//        when(this.userRepository.save((User) any())).thenReturn(user);
//
//        // Act and Assert
//        assertSame(user, this.userService.SignIn(new InsertUserDto()));
//        verify(this.userRepository).save((User) any());
//    }
//
//
//    @Test
//    void testSignIn2() {
//        // Arrange
//        when(this.userRepository.save((User) any())).thenThrow(new RoleNotFoundException("Rolename"));
//
//        // Act and Assert
//        assertThrows(RoleNotFoundException.class, () -> this.userService.SignIn(new InsertUserDto()));
//        verify(this.userRepository).save((User) any());
//    }
}

