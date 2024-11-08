package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    Long saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findConditionSchedule(String author, String modification_date);

    ScheduleResponseDto findScheduleById(Long id);

    ScheduleResponseDto updateSchedule(Long id, ScheduleRequestDto requestDto);

    void deleteSchedule(Long id);
}
