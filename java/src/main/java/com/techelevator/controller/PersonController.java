package com.techelevator.controller;

import com.techelevator.dao.PersonDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Person;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("people")
public class PersonController {
    private PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }

    @GetMapping
    public List<Person> getPeople() {
        return personDao.getPeople();
    }

    @GetMapping("/{id}")
    public Person personById(@PathVariable int personId) {
        Person person = personDao.getPersonById(personId);
        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found.");
        }
        else {
            return person;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        return personDao.createPerson(person);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePerson(@RequestBody Person person, @PathVariable int personId) {
        try {
            person.setPersonId(personId);
            Person updatedPerson = personDao.updatePerson(person);
        }
        catch (DaoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found");
        }
    }
}
