package academy.mindswap.flight.services;

import academy.mindswap.flight.commands.EmailData;
import academy.mindswap.flight.gatewayemail.EmailTemplate;

public interface EmailService {

    void sendEmail(EmailData emailData, EmailTemplate template);
}
