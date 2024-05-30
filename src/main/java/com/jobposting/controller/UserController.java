package com.jobposting.controller;

import com.jobposting.config.security.JwtService;
import com.jobposting.dto.user.UserResponseDto;
import com.jobposting.entity.User;
import com.jobposting.service.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final JwtService jwtService;
    private final UserService userService;

    @GetMapping("/fetch-user-info")
    public ResponseEntity<UserResponseDto> fetchUserInfo(@RequestHeader("Authorization") String authorizationHeader){
        String token = authorizationHeader.substring(7);
        UserResponseDto userResponseDto = userService.getUser(jwtService.extractUsername(token));
        return ResponseEntity.ok(userResponseDto);
    }
}
