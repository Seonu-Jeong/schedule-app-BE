package com.example.schedule.repository;

import com.example.schedule.dto.UserDto;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDto> findUserByNameAndPassword(String author, String password);

    Optional<UserDto> findUserByScheduleId(Long id);

    void updateUser(UserDto userDto, String author);
}
