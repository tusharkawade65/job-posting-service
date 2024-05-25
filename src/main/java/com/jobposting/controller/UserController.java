package com.jobposting.controller;

import com.jobposting.dto.AuthenticationRequest;
import com.jobposting.dto.AuthenticationResponse;
import com.jobposting.dto.RegisterRequest;
import com.jobposting.entity.Role;
import com.jobposting.service.user.UserService;
import com.jobposting.service.user.security.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth/")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        request.setRole(Role.USER);
        return ResponseEntity.ok(authenticationService.register(request));
    }



}
