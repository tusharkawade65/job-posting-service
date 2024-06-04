package com.jobposting.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class DemoController {
    @GetMapping("/test")
    public ResponseEntity<String> sayHello() {
        return ResponseEntity.ok("hello from user secure endpoint");
    }

}
