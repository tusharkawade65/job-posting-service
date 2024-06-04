package com.jobposting.dto.security;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.jobposting.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponse {

    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("message")
    private String message;
    private UserDto userDto;
}