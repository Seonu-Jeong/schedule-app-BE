package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

    private final ScheduleServiece scheduleServiece;

    public ScheduleController(ScheduleServiece scheduleServiece){
        this.scheduleServiece = scheduleServiece;
    }

    @PostMapping
    public ResponseEntity<Long> createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        Long registered_schedule_id = scheduleServiece.saveSchedule(requestDto);

        HttpStatus httpStatus = HttpStatus.CREATED;

        if(registered_schedule_id == -1L){
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(registered_schedule_id, httpStatus);
    }


}
