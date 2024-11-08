package com.example.schedule.repository;

import com.example.schedule.dto.UserDto;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDto> findUserByNameAndPassword(String author, String password);
}
