package com.example.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleResponseDto {
    private long id;
    private String todo;
    private String modification_date;
    private String creation_date;
    private String author;
}
