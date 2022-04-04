package academy.mindswap.flight.services;

import academy.mindswap.flight.commands.FlightDTO;
import academy.mindswap.flight.commands.UserDto;
import academy.mindswap.flight.converters.FlightConverter;
import academy.mindswap.flight.converters.UserConverter;
import academy.mindswap.flight.exceptions.FlightNotFoundException;
import academy.mindswap.flight.persistence.models.Flight;
import academy.mindswap.flight.persistence.repositories.FlightRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FlightService {

    private static final Logger LOGGER = LogManager.getLogger(FlightService.class);

    @Autowired
    private FlightConverter flightConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private FlightRepository flightRepository;

    public FlightDTO getFlightByNumber(String flightNumber) {

        return flightConverter.convertToDTO(flightRepository.findByFlightNumber(String.valueOf(flightNumber)).get());
    }

    public FlightDTO addFlight(FlightDTO flightDTO) {

        if (flightDTO.getFlightNumber() != null) {
            LOGGER.log(Level.INFO, "Adding flight: " + flightDTO.getFlightNumber());
            Flight flight = flightRepository.save(flightConverter.convertToEntity(flightDTO));
            return flightConverter.convertToDTO(flight);
        }
        LOGGER.log(Level.INFO, "Flight already exists: " + flightDTO.getFlightNumber());
        return null;
    }

    public List<FlightDTO> getFlightByArrival(String arrival) {

        LOGGER.log(Level.INFO, "Getting flight by arrival: " + arrival);
        return (flightRepository.findAll()
                .stream()
                .filter(i -> i.getArrivalAirport().equals(arrival))
                .map(flightConverter::convertToDTO)
                .collect(Collectors.toList()));

    }

    public List<FlightDTO> getFlightByDeparture(String departure) {

        LOGGER.log(Level.INFO, "Getting flight by departure: " + departure);
        return (flightRepository.findAll()
                .stream()
                .filter(i -> i.getDepartureAirport().equals(departure))
                .map(flightConverter::convertToDTO)
                .collect(Collectors.toList()));

    }

    public List<FlightDTO> getAllFlights() {

        LOGGER.log(Level.INFO, "Getting all flights");

        return (flightRepository.findAll()
                .stream()
                .map(flightConverter::convertToDTO)
                .collect(Collectors.toList()));

    }

    public FlightDTO updateFlight(FlightDTO flightDTO) {

        Flight flight = flightRepository
                .findByFlightNumber(flightConverter.convertToEntity(flightDTO).getFlightNumber()).orElseThrow(
                        () -> {
                            LOGGER.log(Level.INFO, "Flight not found");
                            return new FlightNotFoundException(flightDTO.getFlightNumber());
                        });

        LOGGER.log(Level.INFO, "Updating flight: " + flightDTO.getFlightNumber());
        return flightConverter.convertToDTO(flightRepository
                .save(flightConverter.convertToEntity(flightDTO)));
    }

    public FlightDTO deleteFlight(String flightNumber) {
        LOGGER.log(Level.INFO, "Deleting flight: " + flightNumber);
        flightRepository.deleteByFlightNumber(flightNumber);
        return null;
    }

    public List<UserDto> getPassengersPerFlight(String flightNumber) {
        return flightRepository.findByFlightNumber(flightNumber)
                .orElseThrow(() -> {
                    LOGGER.log(Level.INFO, "Flight not found");
                    return new FlightNotFoundException(flightNumber);
                })

                .getUsers()
                .stream()
                .map(userConverter::convertToDto).collect(Collectors.toList());
    }

}
