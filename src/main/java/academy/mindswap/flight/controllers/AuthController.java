package academy.mindswap.flight.controllers;


import academy.mindswap.flight.commands.AuthToken;
import academy.mindswap.flight.commands.InsertUserDto;
import academy.mindswap.flight.commands.LoginRequest;
import academy.mindswap.flight.commands.UserDto;
import academy.mindswap.flight.config.TokenProvider;
import academy.mindswap.flight.persistence.models.User;
import academy.mindswap.flight.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Objects;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenProvider jwtTokenUtil;

    @Autowired
    private UserService userService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> generateToken(@RequestBody LoginRequest loginUser)
            throws AuthenticationException {

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginUser.getEmail(),
                        loginUser.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        final String token = jwtTokenUtil.generateToken(authentication);
        return ResponseEntity.ok(new AuthToken(token));
    }

    @RequestMapping(value="/register", method = RequestMethod.POST)
    public UserDto saveUser(@RequestBody InsertUserDto user){
        return userService.register(user);
    }

//    @PreAuthorize("hasRole('USER')")
//    @RequestMapping(value="/userping", method = RequestMethod.GET)
//    public String userPing(){
//        return "Any User Can Read This";
//    }
}
