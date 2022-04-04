package academy.mindswap.flight.persistence.repositories;

import academy.mindswap.flight.persistence.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String name);
}
