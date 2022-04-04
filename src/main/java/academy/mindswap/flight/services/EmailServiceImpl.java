package academy.mindswap.flight.services;


import academy.mindswap.flight.commands.EmailData;
import academy.mindswap.flight.gatewayemail.EmailGateway;
import academy.mindswap.flight.gatewayemail.EmailTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
//
//@Service
//public class EmailServiceImpl implements EmailService {
//
//
////    private String baseAppUrl;
//
//    private final EmailGateway emailGateway;
//
//    public EmailServiceImpl(EmailGateway emailGateway) {
//        this.emailGateway = emailGateway;
//    }

//    @Override
//    public void sendEmail(EmailData emailData, EmailTemplate template) {
//
//        String name = emailData.getName();
//
//        Map<String, String> variables = new HashMap<>();
//        variables.put("name", name);
//
//
//        emailGateway.sendEmail(
//                emailData.getEmail(),
//                variables,
//                template
//        );
//    }
//}
