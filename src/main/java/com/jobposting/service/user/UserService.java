package com.jobposting.service.user;

import com.jobposting.dto.user.UserResponseDto;
import com.jobposting.entity.User;
import com.jobposting.repository.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class UserService implements IUserService{

    private final UserRepo userRepo;
    @Override
    public User saveUser(User user) {
        log.info(user.getFirstname()+" saving new user to database");
        return userRepo.save(user);
    }

    @Override
    public UserResponseDto getUser(String email) {
        log.info("finding user by email");
        User user= userRepo.findByEmail(email).orElseThrow();
        return new UserResponseDto(user);
    }

    @Override
    public List<User> getUsers() {
        log.info("request to get all users..! implement pagination ");
        return userRepo.findAll();
    }
}
