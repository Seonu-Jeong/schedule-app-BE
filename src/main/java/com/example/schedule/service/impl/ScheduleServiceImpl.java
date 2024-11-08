package com.example.schedule.service.impl;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.dto.UserDto;
import com.example.schedule.repository.ScheduleRepository;
import com.example.schedule.repository.UserRepository;
import com.example.schedule.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
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
    @Transactional
    public Long saveSchedule(ScheduleRequestDto requestDto) {

        Optional<UserDto> user = userRepository.findUserByNameAndPassword(
                requestDto.getAuthor(), requestDto.getPassword());


        Long result = -1L;

        if(user.isPresent()){
            result = scheduleRepository.saveSchedule(requestDto, user.get().getId());
        }

        return result;
    }

    @Override
    public List<ScheduleResponseDto> findConditionSchedule(String author, String modification_date) {
        return scheduleRepository.findScheduleByAuthorAndModificationDate(author, modification_date);
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        return scheduleRepository.findScheduleById(id);
    }

    @Override
    @Transactional
    public int updateSchedule(Long id, ScheduleRequestDto requestDto) throws Exception{

        Optional<UserDto> user = userRepository.findUserByScheduleId(id);

        if(user.isPresent())
            userRepository.updateUser(user.get(), requestDto.getAuthor());
        else
            throw new Exception("no exist user");

        return scheduleRepository.updateSchedule(id, requestDto);
    }

    @Override
    public void deleteSchedule(Long id) {
        scheduleRepository.deleteSchedule(id);
    }


}
