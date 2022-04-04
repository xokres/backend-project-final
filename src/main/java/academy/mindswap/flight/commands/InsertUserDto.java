package academy.mindswap.flight.commands;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InsertUserDto {
    @NotBlank( message= "Name cannot be empty")
    private String name;
    @Email(message = "Invalid email address")
    @NotBlank( message= "Email cannot be empty")
    private String email;
    @Size(min = 4, max = 8, message = "Password must be at least 4 characters long")
    @NotBlank( message= "Password cannot be empty")
    private String password;
    @Size(min = 4, max = 8, message = "Password must be at least 4 characters long")
    @NotBlank( message= "Password cannot be empty")
    private String retypePassword;
    private Integer age;
    private Long identificationNumber;
}
