package com.example.schedule.service.impl;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.UserDto;
import com.example.schedule.service.ScheduleService;
import org.apache.catalina.UserDatabase;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {

    final private ScheduleRepository scheduleRepository;

    final private UserRepository userRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, UserRepository userRepository){
        this.scheduleRepository = scheduleRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Long saveSchedule(ScheduleRequestDto requestDto) {

        Optional<UserDto> user = userRepository.findUserByNameAndPassword(
                requestDto.getAuthor(), requestDto.getPassword());


        Long result = -1L;

        if(user.isPresent()){
            result = scheduleRepository.saveSchedule(requestDto, user.get().getId());
        }

        return result;
    }
}
