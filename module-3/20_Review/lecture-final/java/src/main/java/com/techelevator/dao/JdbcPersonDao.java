package com.techelevator.dao;

import com.techelevator.model.Person;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JdbcPersonDao implements PersonDao {
    private final JdbcTemplate jdbcTemplate;

    public JdbcPersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Person getPerson(int id) {
        String sql = "SELECT person_id, name FROM person WHERE person_id = ?";

        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, id);

        Person person = null;

        if (rowSet.next()) {
            person = new Person();
            person.setId(id);
            person.setName(rowSet.getString("name"));
        }

        return person;
    }
}
