package academy.mindswap.flight;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy(proxyTargetClass=true)

public class FlightApplication {

	public static void main(String[] args) {

		SpringApplication.run(FlightApplication.class, args);
				//.start();
	}



}
