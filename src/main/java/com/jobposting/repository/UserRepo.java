package com.jobposting.repository;

import com.jobposting.entity.User;
import com.jobposting.exceptions.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);
    Optional<User> findByMobileNo(String mobileNo);
}
