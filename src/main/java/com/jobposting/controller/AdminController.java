package com.jobposting.controller;

import com.jobposting.dto.TestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @GetMapping("/test")
    public TestDto test() {
        return new TestDto(200,"Welcome to admin portal");
    }
}
