package com.jobposting.controller;

import com.jobposting.dto.TestDto;
import com.jobposting.entity.Test;
import com.jobposting.repository.TestRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/home")
@CrossOrigin(origins = "*")
@Slf4j

public class HomeController {
    TestRepo testRepo;

    HomeController(TestRepo testRepo){
        this.testRepo=testRepo;
    }

    @GetMapping("/")
    public TestDto test() {
        log.info("up and running");
        return new TestDto(200,"up and running");
    }
    @GetMapping("/test/db")
    public TestDto testDb(){
       Optional<Test> test= testRepo.findById(1);
       if(test.isPresent()) {
           log.info("Db connection established");
           return new TestDto(200,test.get().getTestText());
       }
       log.info("Db connection failed");
       return new TestDto(404,"db error");
    }
}
