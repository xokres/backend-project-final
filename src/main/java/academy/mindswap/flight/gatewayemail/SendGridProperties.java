package academy.mindswap.flight.gatewayemail;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "mailing.sendgrid")
public class SendGridProperties {

    private String apiKey;
    private boolean enabled;
}
