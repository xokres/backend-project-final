package academy.mindswap.flight.commands;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FlightDTO {

    private String flightNumber;
    private String departureDate;
    private String arrivalDate;
    private String departureTime;
    private String arrivalTime;
    private String departureAirport;
    private String arrivalAirport;
}

