package academy.mindswap.flight.persistence.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "users")
//@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long idNumber;
//    @Column
//    private String firstName;
//    @Column
//    private String lastName;
    @Column
    private String name;
    @Column
    private String email;
    @Column
    private String password;
    @Column
    private Integer age;
    @Column
    private Integer identificationNumber;


    @JsonIgnore
    @ManyToMany//(fetch = FetchType.EAGER)
    @JoinTable(name = "User_Flights", joinColumns = @JoinColumn(name = "userId"), //referencedColumnName = "userId"),
            inverseJoinColumns = @JoinColumn(name = "flight_number"))
    @Column
    private Set<Flight> flights;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "userId"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
    private Set<Role> roles;

    @Override
    public String toString() {
        return "User{" +
                "idNumber=" + idNumber +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }



}

