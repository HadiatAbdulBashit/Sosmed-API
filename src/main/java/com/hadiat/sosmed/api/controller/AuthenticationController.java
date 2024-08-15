package com.hadiat.sosmed.api.controller;

import com.hadiat.sosmed.api.service.AuthenticationService;
import com.hadiat.sosmed.api.utils.dto.*;
import com.hadiat.sosmed.api.utils.responseWrapper.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDTO request) {
        return Response.renderJSON(
                authenticationService.register(request),
                "Register Success",
                HttpStatus.CREATED
        );
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@RequestBody AuthenticationRequestDTO request) {
        return Response.renderJSON(
                authenticationService.authenticate(request),
                "Login Success",
                HttpStatus.OK
        );
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequestDTO request) {
        return Response.renderJSON(
                authenticationService.refreshToken(request),
                "Token Updated",
                HttpStatus.OK
        );
    }

}
