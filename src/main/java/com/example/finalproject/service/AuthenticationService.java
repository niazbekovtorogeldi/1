package com.example.finalproject.service;

import com.example.finalproject.dto.authencationResponse.AdminTokenRequest;
import com.example.finalproject.dto.authencationResponse.AuthenticationResponse;
import com.example.finalproject.dto.authencationResponse.SignInRequest;
import com.example.finalproject.dto.authencationResponse.SignUpRequest;

public interface AuthenticationService {
//    AuthenticationResponse adminToken(AdminTokenRequest adminTokenResponse);
//

    AuthenticationResponse signUp(SignUpRequest signUpRequest);

    AuthenticationResponse signIn(SignInRequest signInRequest);
}
