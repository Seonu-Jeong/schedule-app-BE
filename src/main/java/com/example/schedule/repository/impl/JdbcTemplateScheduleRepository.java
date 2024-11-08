package com.example.schedule.repository.impl;

import com.example.schedule.dto.ScheduleRequestDto;
import com.example.schedule.dto.ScheduleResponseDto;
import com.example.schedule.repository.ScheduleRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class JdbcTemplateScheduleRepository implements ScheduleRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateScheduleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Long saveSchedule(ScheduleRequestDto requestDto, Long id) {
        // INSERT Query를 직접 작성하지 않아도 된다.
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingColumns(
                "todo", "user_id"
        ).usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("todo", requestDto.getTodo());
        parameters.put("user_id", id);

        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        return key.longValue();
    }

    @Override
    public List<ScheduleResponseDto> findScheduleByAuthorAndModificationDate(String author, String modificationDate) {

        String query = "select * from schedule where user_id = (SELECT id FROM user WHERE name = '"+
                author+"')";

        if(modificationDate != null){
            query += " and modification_date = "+modificationDate;
        }

        query += " order by modification_date desc";

        return jdbcTemplate.query(query, scheduleRowMapper());
    }



    private RowMapper<ScheduleResponseDto> scheduleRowMapper() {
        return new RowMapper<ScheduleResponseDto>() {
            @Override
            public ScheduleResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleResponseDto(
                        rs.getLong("id"),
                        rs.getString("todo"),
                        rs.getString("modification_date"),
                        rs.getString("creation_date")
                );
            }

        };
    }

    @Override
    public ScheduleResponseDto findScheduleById(Long id) {
        List<ScheduleResponseDto> result = jdbcTemplate.query("select * from schedule where id = ?", scheduleRowMapper(), id);

        if(result.isEmpty()){
            return null;
        }

        return result.get(0);
    }

    @Override
    public int updateSchedule(Long id, ScheduleRequestDto requestDto) {

        return jdbcTemplate.update("update schedule set todo = ? where id = ?",
                requestDto.getTodo(), id);
    }

    @Override
    public void deleteSchedule(Long id) {
        jdbcTemplate.update("delete from schedule where id = ?", id);
    }
}
