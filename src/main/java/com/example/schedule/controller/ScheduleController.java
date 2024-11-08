package com.example.schedule.controller;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RequestMapping("/api/schedules")
@RestController
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping
    public ResponseEntity<Long> createSchedule(@RequestBody ScheduleRequestDto requestDto) {

        Long registered_schedule_id = scheduleService.saveSchedule(requestDto);

        HttpStatus httpStatus = HttpStatus.CREATED;

        if (registered_schedule_id == -1L) {
            httpStatus = HttpStatus.BAD_REQUEST;
        }

        return new ResponseEntity<>(registered_schedule_id, httpStatus);
    }


    @GetMapping
    public List<ScheduleResponseDto> findScheduleList(
            @RequestParam(required = false) String modification_date, @RequestParam String author) {

        List<ScheduleResponseDto> responseList = scheduleService.findConditionSchedule(author, modification_date);

        return responseList;
    }


    @GetMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> findScheduleById(@PathVariable Long id) {

        return new ResponseEntity<>(scheduleService.findScheduleById(id), HttpStatus.OK);
    }


    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(
            @PathVariable Long id, @RequestBody ScheduleRequestDto requestDto) {

        return new ResponseEntity<>(scheduleService.updateSchedule(id, requestDto), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Long id) {

        scheduleService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
