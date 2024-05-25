package com.jobposting.controller;

import com.jobposting.dto.security.AuthenticationRequest;
import com.jobposting.dto.security.AuthenticationResponse;
import com.jobposting.dto.security.RegisterRequest;
import com.jobposting.entity.Role;
import com.jobposting.exceptions.DataValidationException;
import com.jobposting.service.user.UserService;
import com.jobposting.service.user.security.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
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
            @RequestBody @Valid RegisterRequest request,
         BindingResult bindingResult
    ) {
        request.setRole(Role.USER);
        if(bindingResult.hasErrors()){
            throw new DataValidationException();
        }
        return ResponseEntity.ok(authenticationService.register(request));
    }

}
