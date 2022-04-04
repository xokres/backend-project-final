package academy.mindswap.flight.converters;

import academy.mindswap.flight.commands.BookFlightDto;
import academy.mindswap.flight.commands.FlightDTO;
import academy.mindswap.flight.persistence.models.Flight;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookFlightConverter {


    @Autowired
    private ModelMapper modelMapper;


    public BookFlightDto convertToDTO(Flight flight) {
        return modelMapper.map(flight, BookFlightDto.class);
    }

    public Flight convertToEntity(BookFlightDto flightDTO) {
        return modelMapper.map(flightDTO, Flight.class);
    }
}
