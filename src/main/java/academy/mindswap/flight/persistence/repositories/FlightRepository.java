package academy.mindswap.flight.persistence.repositories;

import academy.mindswap.flight.persistence.models.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlightRepository extends JpaRepository<Flight, String> {
    Optional<Flight> findByFlightNumber(String flightNumber);

    Optional<Flight> findByArrivalAirport(String destination);

    void deleteByFlightNumber(String flightNumber);
}
