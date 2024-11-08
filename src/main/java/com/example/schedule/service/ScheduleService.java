package com.example.schedule.service;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleService {

    Long saveSchedule(ScheduleRequestDto requestDto);

    List<ScheduleResponseDto> findConditionSchedule(String author, String modification_date);

    ScheduleResponseDto findScheduleById(Long id);

    int updateSchedule(Long id, ScheduleRequestDto requestDto) throws Exception;

    void deleteSchedule(Long id);
}
