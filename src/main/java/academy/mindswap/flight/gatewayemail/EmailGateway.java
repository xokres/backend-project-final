package academy.mindswap.flight.gatewayemail;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public interface EmailGateway {


    void sendEmail(String email, String name, EmailTemplate template);

}
