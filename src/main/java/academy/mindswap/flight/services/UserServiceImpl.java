package academy.mindswap.flight.services;

import academy.mindswap.flight.commands.EmailData;
import academy.mindswap.flight.commands.InsertUserDto;
import academy.mindswap.flight.commands.UserDto;
import academy.mindswap.flight.converters.UserConverter;
import academy.mindswap.flight.exceptions.InvalidUserId;
import academy.mindswap.flight.exceptions.UserNotFoundException;
import academy.mindswap.flight.gatewayemail.EmailGateway;
import academy.mindswap.flight.gatewayemail.EmailTemplate;
import academy.mindswap.flight.gatewayemail.SendGridGateway;
import academy.mindswap.flight.persistence.models.Role;
import academy.mindswap.flight.persistence.models.User;
import academy.mindswap.flight.persistence.repositories.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service("userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    private static final Logger LOGGER = LogManager.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserConverter userConverter;

    @Autowired
    private RoleService roleService;
    @Autowired
    private BCryptPasswordEncoder bcryptEncoder;
//    @Autowired
//    private EmailServiceImpl emailService;
    @Autowired
    private EmailGateway emailGateway;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.
                findByEmail(email)
                .orElseThrow(() -> new UserNotFoundException(userRepository.findByEmail(email).get().getIdNumber()));
        return new org.springframework.security
                .core.userdetails.User(user.getEmail(), user.getPassword(), getAuthority(user));
    }

    private Set<SimpleGrantedAuthority> getAuthority(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }


    @Override
    public UserDto save(UserDto userDto) {
        return userConverter
                .convertToDto(userRepository
                        .save(userConverter.convertToEntity(userDto)));
    }

    @Override
    public UserDto register(InsertUserDto user) {
        User userEntity = userConverter.convertToEntity(user);
        userEntity.setPassword(bcryptEncoder.encode(user.getPassword()));

        Role role = roleService.findByName("USER");
        Set<Role> roleSet = new HashSet<>();
        roleSet.add(role);
        userEntity.setRoles(roleSet);
        User newUser =  userRepository.save(userEntity);

        emailGateway.sendEmail(newUser.getEmail(), newUser.getName(), EmailTemplate.WELCOME_EMAIL);

//        emailService.sendEmail(
//                EmailData.builder()
//                        .email(user.getEmail())
//                        .build(),
//                EmailTemplate.WELCOME_EMAIL);

        return userConverter.convertToDto(newUser);
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream().map(userConverter::convertToDto).collect(Collectors.toList());
    }

    @Override
    public UserDto findOne(String email) {
        return userConverter.
                convertToDto(
                        userRepository.
                                findByEmail(email)
                                .orElseThrow(() -> new UserNotFoundException(userRepository.findByEmail(email).get().getIdNumber())
                                )
                );
    }


    @Override
    public UserDto findById(Long id) throws InvalidUserId {
        return userConverter
                .convertToDto(userRepository
                        .findById(id).orElseThrow(() -> new InvalidUserId(Long.toString(id))));
    }


}
