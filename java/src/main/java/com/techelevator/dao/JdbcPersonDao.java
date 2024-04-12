package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Person;
import com.techelevator.model.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.BadSqlGrammarException;
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
    private final String SELECT_PERSON = "SELECT person_id, user_id, first_name, last_name, email, is_available_weekdays, is_available_weekends, volunteering_interest, is_approved, token " +
            "FROM people";

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
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }

        return people;
    }

    @Override
    public Person getPersonById(int personId) {
        String sql = SELECT_PERSON + "WHERE person_id ?";
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
        String sql = "INSERT INTO people (person_id, user_id, first_name, last_name, email, is_available_weekdays, is_available_weekends, volunteering_interest FROM people)" +
                "VALUES (?,?,?,?,?,?,?,?) RETURNING person_id";
        Person newPerson = null;
        try {
            int personId = jdbcTemplate.queryForObject(sql, int.class,
                    person.getPersonId(), person.getUserId(), person.getFirstName(), person.getLastName(), person.getEmail(), person.isAvailableWeekdays(), person.isAvailableWeekends(), person.getVolunteeringInterest(), person.getIsApproved(), person.getToken());
            newPerson = getPersonById(personId);
        }
        catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        }


        return newPerson;
    }

    @Override
    public Person updatePerson(Person updateIndividual) {
        Person updatedPerson = null;
        String sql = "UPDATE project " +
                "SET first_name = ?" +
                "last_name = ?" +
                "email = ?" +
                "is_available_weekdays = ?" +
                "is_available_weekends = ?" +
                "volunteering_interest = ?" +
                "is_approved = ?" +
                "token = ?" +
                "WHERE person_id = ?";
        try {
            int rowsUpdated = jdbcTemplate.update(sql,
                    updateIndividual.getFirstName(), updateIndividual.getLastName(), updateIndividual.getEmail(), updateIndividual.isAvailableWeekdays(), updateIndividual.isAvailableWeekends(), updateIndividual.getVolunteeringInterest(), updateIndividual.getIsApproved(), updateIndividual.getToken());
            if (rowsUpdated == 1) {
                updatedPerson = getPersonById(updateIndividual.getPersonId());
            }
            else {
                throw new DaoException("updatePerson not used");
            }
        }
        catch (CannotGetJdbcConnectionException ex) {
            throw new DaoException("Connection error.", ex);
        }
        catch (DataIntegrityViolationException ex) {
            throw new DaoException("Error with data integrity.", ex);
        }
        catch (BadSqlGrammarException ex) {
            throw new DaoException("Please review your SQL string.", ex);
        }

        return updatedPerson;
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
