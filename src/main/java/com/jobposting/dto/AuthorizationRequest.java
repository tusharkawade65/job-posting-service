package com.jobposting.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder

public class AuthorizationRequest {
    private String email;
    private String password;
}
