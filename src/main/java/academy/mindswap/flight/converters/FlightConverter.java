package academy.mindswap.flight.converters;

import academy.mindswap.flight.commands.FlightDTO;
import academy.mindswap.flight.persistence.models.Flight;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FlightConverter {

    @Autowired
    private ModelMapper modelMapper;


    public FlightDTO convertToDTO(Flight flight) {
        return modelMapper.map(flight, FlightDTO.class);
    }

    public Flight convertToEntity(FlightDTO flightDTO) {
        return modelMapper.map(flightDTO, Flight.class);
    }
}
