package academy.mindswap.flight.commands;

import academy.mindswap.flight.persistence.models.Role;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {


    private Long idNumber;
    @NotBlank( message= "Name cannot be empty")
    private String name;
    @Email(message = "Invalid email address")
    @NotBlank( message= "Email cannot be empty")
    private String email;
    private Integer age;
    private Set<Role> roles;
    private Set<FlightDTO> flights;

}
