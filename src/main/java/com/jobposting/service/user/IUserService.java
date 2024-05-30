package com.jobposting.service.user;

import com.jobposting.dto.user.UserResponseDto;
import com.jobposting.entity.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);
    UserResponseDto getUser(String email);
    List<User> getUsers();
}
