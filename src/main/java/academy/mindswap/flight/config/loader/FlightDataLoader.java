package academy.mindswap.flight.config.loader;

import academy.mindswap.flight.persistence.models.Flight;
import academy.mindswap.flight.persistence.repositories.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.LongConsumer;
import java.util.stream.LongStream;


@Component
public class FlightDataLoader {

    @Autowired
    private FlightRepository flightRepository;


    public void flightLoader(){

        flightRepository.save(Flight.builder().flightNumber("FR-001").departureDate("2022-09-01").departureTime("10:00").arrivalDate("2022-09-01").arrivalTime("12:00").departureAirport("LHR").arrivalAirport("OPO").build());
        flightRepository.save(Flight.builder().flightNumber("FR-002").departureDate("2022-09-01").departureTime("10:00").arrivalDate("2022-09-01").arrivalTime("12:00").departureAirport("OPO").arrivalAirport("MAD").build());
        flightRepository.save(Flight.builder().flightNumber("FR-003").departureDate("2022-09-01").departureTime("10:00").arrivalDate("2022-09-01").arrivalTime("12:00").departureAirport("MAD").arrivalAirport("LHR").build());
        flightRepository.save(Flight.builder().flightNumber("FR-004").departureDate("2022-09-01").departureTime("10:00").arrivalDate("2022-09-01").arrivalTime("12:00").departureAirport("STN").arrivalAirport("NTE").build());
        flightRepository.save(Flight.builder().flightNumber("FR-005").departureDate("2022-09-01").departureTime("10:00").arrivalDate("2022-09-01").arrivalTime("12:00").departureAirport("NTE").arrivalAirport("LHR").build());
        flightRepository.save(Flight.builder().flightNumber("FR-006").departureDate("2022-09-01").departureTime("10:00").arrivalDate("2022-09-01").arrivalTime("12:00").departureAirport("LHR").arrivalAirport("STN").build());
        flightRepository.save(Flight.builder().flightNumber("FR-007").departureDate("2022-09-01").departureTime("10:00").arrivalDate("2022-09-01").arrivalTime("12:00").departureAirport("STN").arrivalAirport("OPO").build());
        flightRepository.save(Flight.builder().flightNumber("FR-008").departureDate("2022-09-01").departureTime("17:00").arrivalDate("2022-09-01").arrivalTime("19:00").departureAirport("OPO").arrivalAirport("NYC").build());
        flightRepository.save(Flight.builder().flightNumber("FR-009").departureDate("2022-09-01").departureTime("17:00").arrivalDate("2022-09-01").arrivalTime("19:00").departureAirport("MAD").arrivalAirport("LHR").build());
        flightRepository.save(Flight.builder().flightNumber("FR-010").departureDate("2022-09-01").departureTime("17:00").arrivalDate("2022-09-01").arrivalTime("19:00").departureAirport("LHR").arrivalAirport("STN").build());
        flightRepository.save(Flight.builder().flightNumber("FR-011").departureDate("2022-09-01").departureTime("17:00").arrivalDate("2022-09-01").arrivalTime("19:00").departureAirport("STN").arrivalAirport("NTE").build());
        flightRepository.save(Flight.builder().flightNumber("FR-012").departureDate("2022-09-01").departureTime("17:00").arrivalDate("2022-09-01").arrivalTime("19:00").departureAirport("NTE").arrivalAirport("LHR").build());
        flightRepository.save(Flight.builder().flightNumber("FR-013").departureDate("2022-09-01").departureTime("17:00").arrivalDate("2022-09-01").arrivalTime("19:00").departureAirport("LHR").arrivalAirport("STN").build());
        flightRepository.save(Flight.builder().flightNumber("FR-014").departureDate("2022-09-01").departureTime("17:00").arrivalDate("2022-09-01").arrivalTime("19:00").departureAirport("STN").arrivalAirport("OPO").build());
        flightRepository.save(Flight.builder().flightNumber("FR-015").departureDate("2022-09-01").departureTime("17:00").arrivalDate("2022-09-01").arrivalTime("19:00").departureAirport("OPO").arrivalAirport("LAX").build());

    }

}

