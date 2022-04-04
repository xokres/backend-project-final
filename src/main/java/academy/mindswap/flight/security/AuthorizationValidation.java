package academy.mindswap.flight.security;

//import academy.mindswap.flight.commands.PrincipalDto;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthorizationValidation {

    public boolean isUser(Long userId) {
        return userId.equals(getPrincipal().getUsername());
    }

    private UserDetails getPrincipal() {
        return (UserDetails) SecurityContextHolder.getContext().getAuthentication().getDetails();
    }
}
