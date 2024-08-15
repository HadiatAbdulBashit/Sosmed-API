package com.hadiat.sosmed.api.service;


import com.hadiat.sosmed.api.model.User;
import com.hadiat.sosmed.api.model.enums.TokenType;
import com.hadiat.sosmed.api.utils.dto.*;

public interface AuthenticationService {

    public RegisterResponseDTO register(RegisterRequestDTO request);

    public AuthenticationResponseDTO authenticate(AuthenticationRequestDTO request);

    public RefreshTokenResponseDTO refreshToken(RefreshTokenRequestDTO request);

    public User getUserAuthenticated();

    void saveUserToken(User user, String jwtToken, TokenType tokenType);
}
