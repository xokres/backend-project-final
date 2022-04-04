package academy.mindswap.flight.config.loader;
import academy.mindswap.flight.config.loader.FlightDataLoader;
import academy.mindswap.flight.config.loader.RoleDataLoader;
import academy.mindswap.flight.config.loader.UserDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStoppedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class SetupDataLoader  {

    @Autowired
    private UserDataLoader userDataLoader;
    @Autowired
    private FlightDataLoader flightDataLoader;
    @Autowired
    private RoleDataLoader roleDataLoader;

    private boolean loaded = false;


    @EventListener(classes = { ContextStoppedEvent.class, ContextRefreshedEvent.class })
    public void onApplicationEvent() {
        // TODO Auto-generated method stub
        System.out.println("Flight app up and running");
        if (!loaded) {
            roleDataLoader.createRoles();
            flightDataLoader.flightLoader();
            userDataLoader.loadData();
            loaded = true;
        }
    }
}
