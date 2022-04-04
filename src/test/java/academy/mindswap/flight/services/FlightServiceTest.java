package academy.mindswap.flight.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import academy.mindswap.flight.commands.FlightDTO;
import academy.mindswap.flight.converters.FlightConverter;
import academy.mindswap.flight.converters.UserConverter;
import academy.mindswap.flight.persistence.models.Flight;
import academy.mindswap.flight.persistence.models.User;
import academy.mindswap.flight.persistence.repositories.FlightRepository;

import java.util.ArrayList;

import java.util.HashSet;
import java.util.Optional;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FlightService.class, FlightConverter.class, UserConverter.class, ModelMapper.class})
@ExtendWith(SpringExtension.class)
class FlightServiceTest {
    @MockBean
    private FlightRepository flightRepository;

    @Autowired
    private FlightService flightService;

    @Autowired
    private FlightConverter flightConverter;

    @Test
    void testGetFlightByNumber() {


        // Arrange
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

        // Act
        FlightDTO actualFlightByNumber = this.flightService.getFlightByNumber("42");

        // Assert
        assertEquals("Arrival Airport", actualFlightByNumber.getArrivalAirport());
        assertEquals("42", actualFlightByNumber.getFlightNumber());
        assertEquals("Departure Time", actualFlightByNumber.getDepartureTime());
        assertEquals("2020-03-01", actualFlightByNumber.getDepartureDate());
        assertEquals("Departure Airport", actualFlightByNumber.getDepartureAirport());
        assertEquals("Arrival Time", actualFlightByNumber.getArrivalTime());
        assertEquals("2020-03-01", actualFlightByNumber.getArrivalDate());
        verify(this.flightRepository).findByFlightNumber((String) any());
    }

    @Test
    void testAddFlight() {

        // Arrange, Act and Assert
        assertNull(this.flightService.addFlight(new FlightDTO("42", "2020-03-01", "2020-03-01", "Departure Time",
                "Arrival Time", "Departure Airport", "Arrival Airport")));
    }

    @Test
    void testAddFlight2() {
        // Arrange
        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());
        when(this.flightRepository.save((Flight) any())).thenReturn(flight);

        // Act
        FlightDTO actualAddFlightResult = this.flightService.addFlight(new FlightDTO(null, "2020-03-01", "2020-03-01",
                "Departure Time", "Arrival Time", "Departure Airport", "Arrival Airport"));

        // Assert
        assertEquals("Arrival Airport", actualAddFlightResult.getArrivalAirport());
        assertEquals("42", actualAddFlightResult.getFlightNumber());
        assertEquals("Departure Time", actualAddFlightResult.getDepartureTime());
        assertEquals("2020-03-01", actualAddFlightResult.getDepartureDate());
        assertEquals("Departure Airport", actualAddFlightResult.getDepartureAirport());
        assertEquals("Arrival Time", actualAddFlightResult.getArrivalTime());
        assertEquals("2020-03-01", actualAddFlightResult.getArrivalDate());
        verify(this.flightRepository).save((Flight) any());
    }

    @Test
    void testAddFlight3() {
        // Arrange
        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());
        when(this.flightRepository.save((Flight) any())).thenReturn(flight);

        // Act
        this.flightService.addFlight(flightConverter.convertToDTO(flight));
    }

    @Test
    void testGetFlightByArrival() {


        // Arrange
        when(this.flightRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(this.flightService.getFlightByArrival("Arrival").isEmpty());
        verify(this.flightRepository).findAll();
    }

    @Test
    void testGetFlightByArrival2() {

        // Arrange
        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());

        ArrayList<Flight> flightList = new ArrayList<>();
        flightList.add(flight);
        when(this.flightRepository.findAll()).thenReturn(flightList);

        // Act and Assert
        assertTrue(this.flightService.getFlightByArrival("Arrival").isEmpty());
        verify(this.flightRepository).findAll();
    }

    @Test
    void testGetFlightByArrival3() {
        // Arrange
        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());

        Flight flight1 = new Flight();
        flight1.setArrivalAirport("Arrival Airport");
        flight1.setArrivalDate("2020-03-01");
        flight1.setArrivalTime("Arrival Airport");
        flight1.setDepartureAirport("Arrival Airport");
        flight1.setDepartureDate("2020-03-01");
        flight1.setDepartureTime("Arrival Airport");
        flight1.setFlightNumber("42");
        flight1.setUsers(new HashSet<>());

        ArrayList<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight);
        when(this.flightRepository.findAll()).thenReturn(flightList);

        // Act and Assert
        assertTrue(this.flightService.getFlightByArrival("Arrival").isEmpty());
        verify(this.flightRepository).findAll();
    }



    @Test
    void testGetFlightByDeparture() {

        // Arrange
        when(this.flightRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(this.flightService.getFlightByDeparture("Departure").isEmpty());
        verify(this.flightRepository).findAll();
    }

    @Test
    void testGetFlightByDeparture2() {
        // Arrange
        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());

        ArrayList<Flight> flightList = new ArrayList<>();
        flightList.add(flight);
        when(this.flightRepository.findAll()).thenReturn(flightList);

        // Act and Assert
        assertTrue(this.flightService.getFlightByDeparture("Departure").isEmpty());
        verify(this.flightRepository).findAll();
    }

    @Test
    void testGetFlightByDeparture3() {
        // Arrange
        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());

        Flight flight1 = new Flight();
        flight1.setArrivalAirport("Departure Airport");
        flight1.setArrivalDate("2020-03-01");
        flight1.setArrivalTime("Departure Airport");
        flight1.setDepartureAirport("Departure Airport");
        flight1.setDepartureDate("2020-03-01");
        flight1.setDepartureTime("Departure Airport");
        flight1.setFlightNumber("42");
        flight1.setUsers(new HashSet<>());

        ArrayList<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight);
        when(this.flightRepository.findAll()).thenReturn(flightList);

        // Act and Assert
        assertTrue(this.flightService.getFlightByDeparture("Departure").isEmpty());
        verify(this.flightRepository).findAll();
    }



    @Test
    void testGetAllFlights() {

        // Arrange
        when(this.flightRepository.findAll()).thenReturn(new ArrayList<>());

        // Act and Assert
        assertTrue(this.flightService.getAllFlights().isEmpty());
        verify(this.flightRepository).findAll();
    }

    @Test
    void testGetAllFlights2() {


        // Arrange
        Flight flight = new Flight();
        flight.setArrivalAirport("Getting all flights");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Getting all flights");
        flight.setDepartureAirport("Getting all flights");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Getting all flights");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());

        ArrayList<Flight> flightList = new ArrayList<>();
        flightList.add(flight);
        when(this.flightRepository.findAll()).thenReturn(flightList);

        // Act and Assert
        assertEquals(1, this.flightService.getAllFlights().size());
        verify(this.flightRepository).findAll();
    }

    @Test
    void testGetAllFlights3() {

        // Arrange
        Flight flight = new Flight();
        flight.setArrivalAirport("Getting all flights");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Getting all flights");
        flight.setDepartureAirport("Getting all flights");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Getting all flights");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());

        Flight flight1 = new Flight();
        flight1.setArrivalAirport("Getting all flights");
        flight1.setArrivalDate("2020-03-01");
        flight1.setArrivalTime("Getting all flights");
        flight1.setDepartureAirport("Getting all flights");
        flight1.setDepartureDate("2020-03-01");
        flight1.setDepartureTime("Getting all flights");
        flight1.setFlightNumber("42");
        flight1.setUsers(new HashSet<>());

        ArrayList<Flight> flightList = new ArrayList<>();
        flightList.add(flight1);
        flightList.add(flight);
        when(this.flightRepository.findAll()).thenReturn(flightList);

        // Act and Assert
        assertEquals(2, this.flightService.getAllFlights().size());
        verify(this.flightRepository).findAll();
    }

    @Test
    void testUpdateFlight() {
        // Arrange
        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());

        Flight flight1 = new Flight();
        flight1.setArrivalAirport("Arrival Airport");
        flight1.setArrivalDate("2020-03-01");
        flight1.setArrivalTime("Arrival Time");
        flight1.setDepartureAirport("Departure Airport");
        flight1.setDepartureDate("2020-03-01");
        flight1.setDepartureTime("Departure Time");
        flight1.setFlightNumber("42");
        flight1.setUsers(new HashSet<>());
        when(this.flightRepository.save((Flight) any())).thenReturn(flight1);
        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(Optional.of(flight));

        // Act
        FlightDTO actualUpdateFlightResult = this.flightService.updateFlight(new FlightDTO("42", "2020-03-01", "2020-03-01",
                "Departure Time", "Arrival Time", "Departure Airport", "Arrival Airport"));

        // Assert
        assertEquals("Arrival Airport", actualUpdateFlightResult.getArrivalAirport());
        assertEquals("42", actualUpdateFlightResult.getFlightNumber());
        assertEquals("Departure Time", actualUpdateFlightResult.getDepartureTime());
        assertEquals("2020-03-01", actualUpdateFlightResult.getDepartureDate());
        assertEquals("Departure Airport", actualUpdateFlightResult.getDepartureAirport());
        assertEquals("Arrival Time", actualUpdateFlightResult.getArrivalTime());
        assertEquals("2020-03-01", actualUpdateFlightResult.getArrivalDate());
        verify(this.flightRepository).findByFlightNumber((String) any());
        verify(this.flightRepository).save((Flight) any());
    }


    @Test
    void testUpdateFlight2() {
        // Arrange
        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(new HashSet<>());

        Flight flight1 = new Flight();
        flight1.setArrivalAirport("Arrival Airport");
        flight1.setArrivalDate("2020-03-01");
        flight1.setArrivalTime("Arrival Time");
        flight1.setDepartureAirport("Departure Airport");
        flight1.setDepartureDate("2020-03-01");
        flight1.setDepartureTime("Departure Time");
        flight1.setFlightNumber("42");
        flight1.setUsers(new HashSet<>());
        when(this.flightRepository.save((Flight) any())).thenReturn(flight1);
        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(Optional.of(flight));

        // Act
        this.flightService.updateFlight(flightConverter.convertToDTO(flight1));
    }

    @Test
    void testDeleteFlight() {


        // Arrange
        doNothing().when(this.flightRepository).deleteByFlightNumber((String) any());

        // Act and Assert
        assertNull(this.flightService.deleteFlight("42"));
        verify(this.flightRepository).deleteByFlightNumber((String) any());
    }

    @Test
    void testGetPassengersPerFlight() {

        // Arrange
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

        // Act and Assert
        assertTrue(this.flightService.getPassengersPerFlight("42").isEmpty());
        verify(this.flightRepository).findByFlightNumber((String) any());
    }

    @Test
    void testGetPassengersPerFlight2() {


        // Arrange
        User user = new User();
        user.setAge(1);
        user.setEmail("jane.doe@example.org");
        user.setFlights(new HashSet<>());
        user.setIdNumber(1L);
        user.setIdentificationNumber(1);
        user.setName("Name");
        user.setPassword("test");
        user.setPassword("iloveyou");
//        user.setPermissions("Permissions");
        user.setRoles(new HashSet<>());

        HashSet<User> userSet = new HashSet<>();
        userSet.add(user);

        Flight flight = new Flight();
        flight.setArrivalAirport("Arrival Airport");
        flight.setArrivalDate("2020-03-01");
        flight.setArrivalTime("Arrival Time");
        flight.setDepartureAirport("Departure Airport");
        flight.setDepartureDate("2020-03-01");
        flight.setDepartureTime("Departure Time");
        flight.setFlightNumber("42");
        flight.setUsers(userSet);
        when(this.flightRepository.findByFlightNumber((String) any())).thenReturn(Optional.of(flight));

        // Act and Assert
        assertEquals(1, this.flightService.getPassengersPerFlight("42").size());
        verify(this.flightRepository).findByFlightNumber((String) any());
    }


}

