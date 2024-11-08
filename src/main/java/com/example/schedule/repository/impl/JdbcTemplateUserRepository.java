package com.example.schedule.repository.impl;

import com.example.schedule.dto.UserDto;
import com.example.schedule.repository.UserRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class JdbcTemplateUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcTemplateUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<UserDto> findUserByNameAndPassword(String author, String password) {
        List<UserDto> result = jdbcTemplate.query("select * from user where name = ? and password = ?",
                userRowMapper(), author, password);

        return result.stream().findFirst();
    }

    @Override
    public Optional<UserDto> findUserByScheduleId(Long id) {
        List<UserDto> result = jdbcTemplate.query("select * from user where id = (select user_id from schedule " +
                        "where id = ?)",
                userRowMapper(), id);

        return result.stream().findFirst();
    }

    @Override
    public void updateUser(UserDto userDto, String author) {
        jdbcTemplate.update("update user set name = ? where id = ?",
                author, userDto.getId());
    }


    private RowMapper<UserDto> userRowMapper() {
        return new RowMapper<UserDto>() {
            @Override
            public UserDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new UserDto(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("creation_date"),
                        rs.getString("modification_date")
                );
            }

        };
    }
}
