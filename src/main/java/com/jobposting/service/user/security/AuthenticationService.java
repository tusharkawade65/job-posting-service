package com.jobposting.service.user.security;

import com.jobposting.config.security.JwtService;
import com.jobposting.dto.AuthenticationRequest;
import com.jobposting.dto.AuthenticationResponse;
import com.jobposting.dto.RegisterRequest;
import com.jobposting.entity.User;
import com.jobposting.repository.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final UserRepo repo;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest authorizationRequest) {
        var user = User.builder()
                .firstname(authorizationRequest.getFirstname())
                .lastname(authorizationRequest.getLastname())
                .email(authorizationRequest.getEmail())
                .password(passwordEncoder.encode(authorizationRequest.getPassword()))
                .role(authorizationRequest.getRole())
                .mobileNo(authorizationRequest.getContact())
                .whatsAppConcent(authorizationRequest.isWhatsappConsent())
                .build();
       if((repo.findByEmail(user.getEmail()).isPresent())||(repo.findByMobileNo(user.getMobileNo()).isPresent())){
           return AuthenticationResponse.builder()
                   .accessToken(null)
                   .message("Email or contact already present")
                   .build();
       }
        repo.save(user);
     var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .message("User registered successfully.")
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repo.findByEmail(request.getEmail())
                .orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .build();
    }
}
