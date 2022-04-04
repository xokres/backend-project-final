package academy.mindswap.flight.config;


import academy.mindswap.flight.gatewayemail.SendGridProperties;
import academy.mindswap.flight.security.AuthorizationValidation;
import com.sendgrid.SendGrid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class BeanConfiguration {

    @Autowired
    private SendGridProperties sendGridProperties;

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
    @Bean
    public BCryptPasswordEncoder bcryptEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
//    public Executor taskExecutor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(2);
//        executor.setMaxPoolSize(2);
//        executor.setQueueCapacity(500);
//        executor.setThreadNamePrefix("GithubLookup-pool-");
//        executor.initialize();
//        return executor;
//    }

    @Bean
    public AuthorizationValidation authorized() {
        return new AuthorizationValidation();
    }

    @Bean
    public SendGrid sendGrid() {
        return new SendGrid(sendGridProperties.getApiKey());
    }

}
