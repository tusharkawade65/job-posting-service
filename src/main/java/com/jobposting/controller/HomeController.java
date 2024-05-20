package com.jobposting.controller;

import com.jobposting.dto.TestDto;
import com.jobposting.entity.Test;
import com.jobposting.repository.TestRepo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {
    TestRepo testRepo;

    HomeController(TestRepo testRepo){
        this.testRepo=testRepo;
    }

    @GetMapping("/")
    public TestDto test() {
        return new TestDto(200,"up and running");
    }
    @GetMapping("/test/db")
    public TestDto testDb(){
       Optional<Test> test= testRepo.findById(1);
       if(test.isPresent()) {
           return new TestDto(200,test.get().getTestText());
       }
       return new TestDto(404,"db error");
    }
}
