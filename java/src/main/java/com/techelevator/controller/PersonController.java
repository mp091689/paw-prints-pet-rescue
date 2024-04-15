package com.techelevator.controller;

import com.techelevator.dao.PersonDao;
import com.techelevator.dao.UserDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Person;
import com.techelevator.model.RegisterUserDto;
import com.techelevator.model.User;
import com.techelevator.service.EmailSender;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin
@RequestMapping("people")
public class PersonController {
    private PersonDao personDao;
    private UserDao userDao;
    private EmailSender emailService;

    public PersonController(PersonDao personDao, UserDao userDao, EmailSender emailService) {
        this.personDao = personDao;
        this.userDao = userDao;
        this.emailService = emailService;
    }

    @GetMapping
    public List<Person> getPeople(@RequestParam(defaultValue = "true") boolean isApproved) {
        return personDao.getPeopleByApproved(isApproved);
    }

    @GetMapping("/{id}")
    public Person personById(@PathVariable int id) {
        Person person = personDao.getPersonById(id);
        if (person == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Person not found.");
        } else {
            return person;
        }
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Person createPerson(@RequestBody Person person) {
        if (personDao.getPersonByEmail(person.getEmail()) != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Person already exists.");
        }

        person.setToken(UUID.randomUUID().toString());
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

    @PutMapping("{id}/approve")
    public void approve(@PathVariable int id) {
        Person person = personDao.getPersonById(id);
        if (person.getIsApproved()) {
            return;
        }

        User newUser;
        try {
            RegisterUserDto userDto = new RegisterUserDto();
            userDto.setUsername(person.getEmail());
            userDto.setRole("ROLE_USER");
            String password = UUID.randomUUID().toString();
            userDto.setPassword(password);
            userDto.setConfirmPassword(password);
            if (userDao.getUserByUsername(userDto.getUsername()) != null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists.");
            }
            newUser = userDao.createUser(userDto);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User registration failed.");
        }

        person.setIsApproved(true);
        person.setUserId(newUser.getId());

        try {
            personDao.updatePerson(person);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Opps something went wrong.");
        }

        String msg = "Follow the link to activate your volunteer account http://localhost:5173/reset-password?token=" + person.getToken();
        emailService.send(person.getEmail(), "You are approved as a Volunteer", msg);
    }
}
