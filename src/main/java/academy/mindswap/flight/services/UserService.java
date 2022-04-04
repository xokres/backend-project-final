package academy.mindswap.flight.services;

import academy.mindswap.flight.commands.BookFlightDto;
import academy.mindswap.flight.commands.FlightDTO;
import academy.mindswap.flight.commands.InsertUserDto;
import academy.mindswap.flight.commands.UserDto;
import academy.mindswap.flight.converters.FlightConverter;
import academy.mindswap.flight.converters.UserConverter;
import academy.mindswap.flight.exceptions.InvalidUserId;
import academy.mindswap.flight.exceptions.UserNotFoundException;
import academy.mindswap.flight.exceptions.RoleNotFoundException;
import academy.mindswap.flight.persistence.models.Flight;
import academy.mindswap.flight.persistence.models.Role;
import academy.mindswap.flight.persistence.models.User;
import academy.mindswap.flight.persistence.repositories.FlightRepository;
import academy.mindswap.flight.persistence.repositories.RoleRepository;
import academy.mindswap.flight.persistence.repositories.UserRepository;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public interface UserService {

    UserDto save(UserDto user);
    UserDto register(InsertUserDto user);
    List<UserDto> findAll();
    UserDto findOne(String username);
    UserDto findById(Long id) throws InvalidUserId;


}
