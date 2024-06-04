package com.jobposting.dto.security;

import com.jobposting.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;


@Data
public class RegisterRequest {
    @NotEmpty(message = "First name is required")
    private String firstname;
    private String lastname;
    @NotEmpty(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 8,max = 16,message = "Password must be at least 8 characters long")
    private String password;
    @Size(min = 10, max = 10, message = "Contact number must be 10 digits long")
    @NotBlank(message = "Contact is required")
    private String contact;
    private boolean whatsappConsent;
    @Value("USER")
    private Role role;

}