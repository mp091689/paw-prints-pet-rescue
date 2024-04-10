package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Person;
import com.techelevator.model.User;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class JdbcPersonDao implements PersonDao{
    private final JdbcTemplate jdbcTemplate;
    public JdbcPersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> getPeople() {
        List<Person> people = new ArrayList<>();
        String sql = "SELECT first_name, last_name, email, is_available_weekdays, is_available_weekends, volunteering_interest FROM people";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
            while (results.next()) {
                Person person = mapRowToPerson(results);
                people.add(person);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        }
        return people;
    }

    // map
    private Person mapRowToPerson(SqlRowSet rs) {
        Person person = new Person();
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setEmail(rs.getString("email"));
        person.setAvailableWeekdays(rs.getBoolean("is_available_weekdays"));
        person.setAvailableWeekends(rs.getBoolean("is_available_weekends"));
        person.setVolunteeringInterest(rs.getString("volunteering_interest"));
        return person;
    }
}
