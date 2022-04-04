package academy.mindswap.flight.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import academy.mindswap.flight.commands.BookFlightDto;
import academy.mindswap.flight.commands.UserDto;
import academy.mindswap.flight.converters.BookFlightConverter;
import academy.mindswap.flight.converters.FlightConverter;
import academy.mindswap.flight.converters.UserConverter;
import academy.mindswap.flight.persistence.models.Flight;
import academy.mindswap.flight.persistence.models.User;
import academy.mindswap.flight.persistence.repositories.FlightRepository;
import academy.mindswap.flight.persistence.repositories.RoleRepository;
import academy.mindswap.flight.persistence.repositories.UserRepository;

import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookFlightServiceImpl.class, BookFlightConverter.class, FlightConverter.class,
        RoleService.class, UserConverter.class, ModelMapper.class})
@ExtendWith(SpringExtension.class)
class BookFlightServiceImplTest {
    @Autowired
    private BookFlightServiceImpl bookFlightServiceImpl;

    @MockBean
    private FlightRepository flightRepository;

    @MockBean
    private RoleRepository roleRepository;

    @MockBean
    private UserRepository userRepository;

    @Test
    void testBookFlight() {
        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
        user1.setAge(1);
        user1.setEmail("jane.doe@example.org");
        user1.setFlights(new HashSet<>());
        user1.setIdNumber(1L);
        user1.setIdentificationNumber(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setRoles(new HashSet<>());
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());
        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(Optional.of(flight));

        BookFlightDto bookFlightDto = new BookFlightDto();
        bookFlightDto.setFlightNumber("42");
        bookFlightDto.setPassengerId(123L);

        // Act
        UserDto actualBookFlightResult = this.bookFlightServiceImpl.bookFlight(bookFlightDto);

        // Assert
        assertEquals(1, actualBookFlightResult.getAge().intValue());
        assertTrue(actualBookFlightResult.getRoles().isEmpty());
        assertEquals("Name", actualBookFlightResult.getName());
        assertEquals(1L, actualBookFlightResult.getIdNumber().longValue());
        assertTrue(actualBookFlightResult.getFlights().isEmpty());
        assertEquals("jane.doe@example.org", actualBookFlightResult.getEmail());
        verify(this.userRepository).save((User) any());
        verify(this.userRepository).findById((Long) any());
        verify(this.flightRepository).findByFlightNumber((String) any());
    }


    @Test
    void testBookFlight2() {
        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());

        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());
        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(Optional.of(flight));

        BookFlightDto bookFlightDto = new BookFlightDto();
        bookFlightDto.setFlightNumber("42");
        bookFlightDto.setPassengerId(123L);

        // Act and Assert
        assertNull(this.bookFlightServiceImpl.bookFlight(bookFlightDto));
        verify(this.userRepository).findById((Long) any());
        verify(this.flightRepository).findByFlightNumber((String) any());
    }

    @Test
    void testCancelFlight() {
        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        Optional<User> ofResult = Optional.of(user);

        User user1 = new User();
        user1.setAge(1);
        user1.setEmail("jane.doe@example.org");
        user1.setFlights(new HashSet<>());
        user1.setIdNumber(1L);
        user1.setIdentificationNumber(1);
        user1.setName("Name");
        user1.setPassword("iloveyou");
        user1.setRoles(new HashSet<>());
        when(this.userRepository.save((User) any())).thenReturn(user1);
        when(this.userRepository.findById((Long) any())).thenReturn(ofResult);

        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());
        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(Optional.of(flight));

        BookFlightDto bookFlightDto = new BookFlightDto();
        bookFlightDto.setFlightNumber("42");
        bookFlightDto.setPassengerId(123L);

        // Act
        UserDto actualCancelFlightResult = this.bookFlightServiceImpl.cancelFlight(bookFlightDto);

        // Assert
        assertEquals(1, actualCancelFlightResult.getAge().intValue());
        assertTrue(actualCancelFlightResult.getRoles().isEmpty());
        assertEquals("Name", actualCancelFlightResult.getName());
        assertEquals(1L, actualCancelFlightResult.getIdNumber().longValue());
        assertTrue(actualCancelFlightResult.getFlights().isEmpty());
        assertEquals("jane.doe@example.org", actualCancelFlightResult.getEmail());
        verify(this.userRepository).save((User) any());
        verify(this.userRepository).findById((Long) any());
        verify(this.flightRepository).findByFlightNumber((String) any());
    }



    @Test
    void testCancelFlight2() {

        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("iloveyou");
        user.setRoles(new HashSet<>());
        when(this.userRepository.save((User) any())).thenReturn(user);
        when(this.userRepository.findById((Long) any())).thenReturn(Optional.empty());

        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());
        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(Optional.of(flight));

        BookFlightDto bookFlightDto = new BookFlightDto();
        bookFlightDto.setFlightNumber("42");
        bookFlightDto.setPassengerId(123L);

        // Act and Assert
        assertNull(this.bookFlightServiceImpl.cancelFlight(bookFlightDto));
        verify(this.userRepository).findById((Long) any());
        verify(this.flightRepository).findByFlightNumber((String) any());
    }
}

