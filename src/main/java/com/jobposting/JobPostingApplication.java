package com.jobposting;

import com.jobposting.entity.User;
import com.jobposting.service.user.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class JobPostingApplication {

	public static void main(String[] args) {
		SpringApplication.run(JobPostingApplication.class, args);
	}
}
