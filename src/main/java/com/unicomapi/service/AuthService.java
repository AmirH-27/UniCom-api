package com.unicomapi.service;

import com.unicomapi.config.JwtService;
import com.unicomapi.Auth.AuthenticationRequest;
import com.unicomapi.Auth.AuthenticationResponse;
import com.unicomapi.Auth.RegisterRequest;
import com.unicomapi.model.Role;
import com.unicomapi.model.User;
import com.unicomapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest registerRequest) {
        var user = User.builder()
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .name(registerRequest.getUsername())
                .activationToken("123")
                .isVerified(true)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .authenticationToken(jwtToken)
                .email(registerRequest.getEmail())
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest registerRequest) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        registerRequest.getEmail(),
                        registerRequest.getPassword()
                ));
        return userRepository.findByEmail(registerRequest.getEmail()).map(user -> new AuthenticationResponse(jwtService.generateToken(user), registerRequest.getEmail())).orElse(null);
    }
}
