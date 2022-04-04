package academy.mindswap.flight.gatewayemail;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Email;
import com.sendgrid.helpers.mail.objects.Personalization;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SendGridGateway implements EmailGateway {

    private static final Logger LOGGER = LogManager.getLogger(SendGridGateway.class);

    private static final String FROM_EMAIL = "ptluisfaria@gmail.com";

    private final SendGrid sendGrid;
    private final SendGridProperties sendGridProperties;

    public SendGridGateway(SendGrid sendGrid, SendGridProperties sendGridProperties) {
        this.sendGrid = sendGrid;
        this.sendGridProperties = sendGridProperties;
    }


    @Override
    public void sendEmail(String email, String name, EmailTemplate template) {

        if (!sendGridProperties.isEnabled()) {
            return;
        }

        Email from = new Email(FROM_EMAIL);
        Email to = new Email(email);

        Personalization personalization = new Personalization();
        personalization.addDynamicTemplateData("name", name);
        personalization.addTo(to);

        Mail mail = new Mail();
        mail.setTemplateId(template.getName());
        mail.setFrom(from);
        mail.addPersonalization(personalization);

        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            sendGrid.api(request);

            LOGGER.info(String.format("Email %s sent successfully to %s", template.getName(), email));
        } catch (IOException ex) {
            LOGGER.error(String.format("Failed to send email %s to %s", template.getName(), email));
        }
    }
}
