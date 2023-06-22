package com.example.finalproject.service.impl;

import com.example.finalproject.confic.JwtService;
import com.example.finalproject.dto.authencationResponse.AuthenticationResponse;
import com.example.finalproject.dto.authencationResponse.SignInRequest;
import com.example.finalproject.dto.authencationResponse.SignUpRequest;
import com.example.finalproject.enam.Role;

import com.example.finalproject.entity.User;
import com.example.finalproject.exception.NotFoundException;
import com.example.finalproject.repository.UserRepository;
import com.example.finalproject.service.AuthenticationService;
import jakarta.annotation.PostConstruct;
import jakarta.persistence.EntityExistsException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.ZonedDateTime;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;



    @Override
    public AuthenticationResponse signUp(SignUpRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.email())) {
            throw new EntityExistsException(String.format(
                    "User with email: %s already exists!", signUpRequest.email()));
        }
        int age = signUpRequest.age();
        int experience = signUpRequest.experience();
        Role role = signUpRequest.role();
        if (role == Role.CHEF && age >= 25 && age <= 45 && experience >= 2) {
            User chef = createUser(signUpRequest);
            return saveUser(chef, "CHEF");
        } else if (role == Role.WAITER && age > 18 && age <= 30 && experience >= 1) {
            User waiter = createUser(signUpRequest);
            return saveUser(waiter, "WAITER");
        } else {
            throw new UsernameNotFoundException("Invalid age or experience for the given role!");
        }
    }
    private User createUser(SignUpRequest signUpRequest) {
        User user = new User();
        user.setFirstName(signUpRequest.firstName());
        user.setLastName(signUpRequest.lastName());
        user.setEmail(signUpRequest.email());
        user.setAge(signUpRequest.age());
        user.setExpirense(signUpRequest.experience());
        user.setDateOfBirth(ZonedDateTime.now());
        user.setPhoneNumber(signUpRequest.phoneNumber());
        user.setPassword(passwordEncoder.encode(signUpRequest.password()));
        user.setRole(signUpRequest.role());
        return user;
    }
    private AuthenticationResponse saveUser(User user, String roleName) {
        userRepository.save(user);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse
                .builder().
                token(jwtToken).
                email(user.getEmail()).
                role(user.getRole()).
                build();
    }
    @Override
    public AuthenticationResponse signIn(SignInRequest signInRequest) {

        if (signInRequest.getEmail().isBlank()) {
            log.error("Email doesn't exist!");
            throw new BadCredentialsException("Email doesn't exist!");
        }

        User user = userRepository.getUserByEmail(signInRequest.getEmail()).orElseThrow(() -> {
            log.error("User with email: " + signInRequest.getEmail() + " not found");
            return new NotFoundException("User with email: " + signInRequest.getEmail() + " not found");
        });

        if (!passwordEncoder.matches(signInRequest.getPassword(), user.getPassword())) {
            log.error("Incorrect password!");
            throw new BadCredentialsException("Incorrect password!");
        }


        String jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse
                .builder()
                .email(user.getEmail())
                .role(user.getRole())
                .token(jwtToken)
                .build();
    }


    @PostConstruct
    public void initializeAdmin() {
        User admin = new User();
        admin.setEmail("torogeldi@gmail.com");
        admin.setDateOfBirth(ZonedDateTime.now());
        admin.setPassword(passwordEncoder.encode("torogeldi123"));
        admin.setRole(Role.ADMIN);
        admin.setLastName("nizbekov");
        admin.setFirstName("torogeldi");
        admin.setPhoneNumber("+996505104433");
        if (!userRepository.existsByEmail("torogeldi@gmail.com")) {
            userRepository.save(admin);
        }

    }
}