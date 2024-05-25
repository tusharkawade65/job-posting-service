package com.jobposting.controller;

import com.jobposting.dto.TestDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {
    @GetMapping("/test")
    public TestDto test() {
        return new TestDto(200,"Welcome to admin portal");
    }
}
