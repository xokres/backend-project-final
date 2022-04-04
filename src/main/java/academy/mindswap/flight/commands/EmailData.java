package academy.mindswap.flight.commands;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmailData {

    private String name;
    private String email;
    private String username;
}