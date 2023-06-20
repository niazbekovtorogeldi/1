package com.example.finalproject.api;

import com.example.finalproject.dto.authencationResponse.AuthenticationResponse;
import com.example.finalproject.dto.authencationResponse.SignInRequest;
import com.example.finalproject.dto.authencationResponse.SignUpRequest;
import com.example.finalproject.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationApi {
    private final AuthenticationService authenticationService;


    @PostMapping("/signUp")
    public AuthenticationResponse signUp(@RequestBody SignUpRequest signUpRequest) {
        return authenticationService.signUp(signUpRequest);
    }

    @PostMapping("/signIn")
    public AuthenticationResponse signIn(@RequestBody SignInRequest signIn) {
        return authenticationService.signIn(signIn);
    }
}

