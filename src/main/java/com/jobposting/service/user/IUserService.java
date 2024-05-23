package com.jobposting.service.user;

import com.jobposting.entity.User;

import java.util.List;

public interface IUserService {
    User saveUser(User user);
    User getUser(String email);
    List<User> getUsers();
}
