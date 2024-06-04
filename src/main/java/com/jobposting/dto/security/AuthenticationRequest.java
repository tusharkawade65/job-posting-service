package com.jobposting.dto.security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

    @NotEmpty
    @Email
    private String email;
    @NotEmpty
    @Size(min = 8,max = 16,message = "Password must be at least 8 characters long")
    private String password;
}