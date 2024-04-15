package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Person;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPersonDao implements PersonDao{
    private final JdbcTemplate jdbcTemplate;
    private final String SELECT = "SELECT person_id, user_id, first_name, last_name, email, is_available_weekdays, is_available_weekends, volunteering_interest, is_approved, token FROM people ";

    public JdbcPersonDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Person> getPeopleByApproved(boolean isApproved) {
        List<Person> people = new ArrayList<>();
        try {
            String sql = SELECT + " WHERE is_approved = ? ";
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, isApproved);
            while (results.next()) {
                Person person = mapRowToPerson(results);
                people.add(person);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return people;
    }

    @Override
    public Person getPersonByEmail(String email) {
        String sql = SELECT + " WHERE email = ?";
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, email);
            if (results.next()) {
                return mapRowToPerson(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return null;
    }

    @Override
    public Person getPersonById(int personId) {
        String sql = SELECT + " WHERE person_id = ?";
        Person person = null;
        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(sql, personId);
            if (results.next()) {
                person = mapRowToPerson(results);
            }
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }
        return person;
    }

    @Override
    public Person createPerson(Person person) {
        String sql = "INSERT INTO people (" +
                "first_name, " +
                "last_name, " +
                "email, " +
                "is_available_weekdays, " +
                "is_available_weekends, " +
                "volunteering_interest, " +
                "is_approved, " +
                "token" +
                ") VALUES (?,?,?,?,?,?,?,?) RETURNING person_id";
        Person newPerson = null;
        try {
            int personId = jdbcTemplate.queryForObject(
                    sql,
                    int.class,
                    person.getFirstName(),
                    person.getLastName(),
                    person.getEmail(),
                    person.isAvailableWeekdays(),
                    person.isAvailableWeekends(),
                    person.getVolunteeringInterest(),
                    person.getIsApproved(),
                    person.getToken()
            );
            newPerson = getPersonById(personId);
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return newPerson;
    }

    @Override
    public Person updatePerson(Person person) {
        String sql = "UPDATE people " +
                "SET first_name = ?, " +
                "last_name = ?, " +
                "user_id = ?, " +
                "email = ?, " +
                "is_available_weekdays = ?, " +
                "is_available_weekends = ?, " +
                "volunteering_interest = ?, " +
                "is_approved = ?, " +
                "token = ? " +
                "WHERE person_id = ? ";
        try {
            int rowsUpdated = jdbcTemplate.update(sql,
                    person.getFirstName(),
                    person.getLastName(),
                    person.getUserId(),
                    person.getEmail(),
                    person.isAvailableWeekdays(),
                    person.isAvailableWeekends(),
                    person.getVolunteeringInterest(),
                    person.getIsApproved(),
                    person.getToken(),
                    person.getPersonId()
            );
            if (rowsUpdated == 1) {
                return getPersonById(person.getPersonId());
            }
            throw new DaoException("updatePerson not used");
        } catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Connection error.", ex);
        } catch (DataIntegrityViolationException ex) {
            throw new DaoException("Error with data integrity.", ex);
        } catch (BadSqlGrammarException ex) {
            throw new DaoException("Please review your SQL string.", ex);
        }
    }

    private Person mapRowToPerson(SqlRowSet rs) {
        Person person = new Person();
        person.setPersonId(rs.getInt("person_id"));
        person.setUserId(rs.getInt("user_id"));
        person.setFirstName(rs.getString("first_name"));
        person.setLastName(rs.getString("last_name"));
        person.setEmail(rs.getString("email"));
        person.setAvailableWeekdays(rs.getBoolean("is_available_weekdays"));
        person.setAvailableWeekends(rs.getBoolean("is_available_weekends"));
        person.setVolunteeringInterest(rs.getString("volunteering_interest"));
        person.setIsApproved(rs.getBoolean("is_approved"));
        person.setToken(rs.getString("token"));
        return person;
    }
}
