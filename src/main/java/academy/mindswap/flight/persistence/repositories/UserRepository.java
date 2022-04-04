package academy.mindswap.flight.persistence.repositories;

import academy.mindswap.flight.persistence.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {

    List<User> findByName(String name);

//    @Query("Select u from User u where u.name = :name")
//    List<User> findByOtherNameThatIWant(@Param("name") String username);

    User findByNameAndPassword(String name, String password);

    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndPassword(String username, String password);

    Optional<User> findById(long id);
}
