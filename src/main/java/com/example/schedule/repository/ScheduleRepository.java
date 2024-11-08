package com.example.schedule.repository;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;

import java.util.List;

public interface ScheduleRepository {
    Long saveSchedule(ScheduleRequestDto requestDto, Long id);

    List<ScheduleResponseDto> findScheduleByAuthorAndModificationDate(String author, String modificationDate);

    ScheduleResponseDto findScheduleById(Long id);

    int updateSchedule(Long id, ScheduleRequestDto requestDto);

    void deleteSchedule(Long id);
}
