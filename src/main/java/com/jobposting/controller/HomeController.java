package com.jobposting.controller;

import com.jobposting.entity.Test;
import com.jobposting.repository.TestRepo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class HomeController {
    TestRepo testRepo;

    HomeController(TestRepo testRepo){
        this.testRepo=testRepo;
    }

    @GetMapping("/")
    public String test() {
        return "V2 Up and running..!";
    }
    @GetMapping("/test/db")
    public String testDb(){
        testRepo.save(new Test(1,"test working"));
       Optional<Test> test= testRepo.findById(1);
       if(test.isPresent()) {
           return test.get().getTestText();
       }
       return "connection failed";
    }
}
