package com.jobposting.controller;

import com.jobposting.dto.TestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @GetMapping("/test")
    @Operation(summary = "Admin Test Endpoint", security = @SecurityRequirement(name = "bearerAuth"))
    public TestDto test() {
        return new TestDto(200,"Welcome to admin portal");
    }
}
