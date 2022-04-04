package academy.mindswap.flight.config.loader;

import academy.mindswap.flight.persistence.models.Role;
import academy.mindswap.flight.persistence.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoleDataLoader {

    @Autowired
    private RoleRepository roleRepository;

    public void createRoles() {

        Role role1 = new Role();
        Role role2 = new Role();
        Role role3 = new Role();

        role1.setName("ADMIN");
        role2.setName("GUEST");
        role3.setName("USER");

        roleRepository.save(role1);
        roleRepository.save(role2);
        roleRepository.save(role3);
    }

}
