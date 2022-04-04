package academy.mindswap.flight.services;

import academy.mindswap.flight.commands.BookFlightDto;
import academy.mindswap.flight.commands.EmailData;
import academy.mindswap.flight.commands.FlightDTO;
import academy.mindswap.flight.commands.UserDto;
import academy.mindswap.flight.converters.BookFlightConverter;
import academy.mindswap.flight.converters.FlightConverter;
import academy.mindswap.flight.converters.UserConverter;
import academy.mindswap.flight.exceptions.FlightNotFoundException;
import academy.mindswap.flight.exceptions.UserNotFoundException;
import academy.mindswap.flight.gatewayemail.EmailTemplate;
import academy.mindswap.flight.gatewayemail.SendGridGateway;
import academy.mindswap.flight.persistence.models.Flight;
import academy.mindswap.flight.persistence.models.User;
import academy.mindswap.flight.persistence.repositories.FlightRepository;
import academy.mindswap.flight.persistence.repositories.RoleRepository;
import academy.mindswap.flight.persistence.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BookFlightServiceImpl {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private FlightRepository flightRepository;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private FlightConverter flightConverter;
    @Autowired
    private BookFlightConverter bookFlightConverter;
    @Autowired
    private SendGridGateway emailGateway;


    public UserDto bookFlight(BookFlightDto flightDTO) {

        User user = userRepository.findById(flightDTO.getPassengerId())
                .orElseThrow(() -> {
                    log.error("User not found");
                    return new UserNotFoundException(flightDTO.getPassengerId());
                });

        Flight flight = flightRepository.findByFlightNumber(flightDTO.getFlightNumber())
                .orElseThrow(() -> {
                    log.error("Flight not found");
                    return new FlightNotFoundException(flightDTO.getFlightNumber());
                });

        user.getFlights().add(flight);
        emailGateway.sendEmail(user.getEmail(), user.getName(), EmailTemplate.BOOKED_FLIGHT);
//        emailService.sendEmail(
//                EmailData.builder()
//                .email(user.getEmail())
//                        .build(),
//                EmailTemplate.BOOKED_FLIGHT);

//        sendGridGateway.sendEmail(user.getEmail(), EmailTemplate.BOOKED_FLIGHT, EmailTemplate.BOOKED_FLIGHT);
        return userConverter.convertToDto(userRepository.save(user));
    }

    public UserDto cancelFlight(BookFlightDto bookFlightDto) {
        User user = userRepository.findById(bookFlightDto.getPassengerId())
                .orElseThrow(() -> {
                    log.error("User not found");
                    return new UserNotFoundException(bookFlightDto.getPassengerId());
                });
        Flight flight = flightRepository.findByFlightNumber(bookFlightDto.getFlightNumber())
                .orElseThrow(() -> {
                    log.error("Flight not found");
                    return new FlightNotFoundException(bookFlightDto.getFlightNumber());
                });


        user.getFlights().remove(flight);
        emailGateway.sendEmail(user.getEmail(), user.getName(), EmailTemplate.CANCELED_FLIGHT);
//        emailService.sendEmail(
//                EmailData.builder()
//                        .email(user.getEmail())
//                        .build(),
//                EmailTemplate.CANCELED_FLIGHT);
        
        return userConverter.convertToDto(userRepository.save(user));
    }
}

