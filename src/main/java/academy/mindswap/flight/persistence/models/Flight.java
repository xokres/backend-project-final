package academy.mindswap.flight.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "flights")
public class Flight {

    @Id
    @Column(name = "flight_number")
    private String flightNumber;
    @Column(name = "departure_date")
    private String departureDate;
    @Column(name = "departure_time")
    private String departureTime;
    @Column(name = "arrival_date")
    private String arrivalDate;
    @Column(name = "arrival_time")
    private String arrivalTime;
    @Column(name = "departure_airport")
    private String departureAirport;
    @Column(name = "arrival_airport")
    private String arrivalAirport;


    @ManyToMany(mappedBy = "flights")
    @JsonIgnore
    private Set<User> users;

}
