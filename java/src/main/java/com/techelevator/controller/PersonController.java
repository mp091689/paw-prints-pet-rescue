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
    public List<Person> getPeople() {
        return personDao.getPeople();
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
        person.setIsApproved(null);

        return personDao.createPerson(person);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updatePerson(@RequestBody Person person, @PathVariable int personId) {
        try {
            person.setPersonId(personId);
            Person updatedPerson = personDao.updatePerson(person);
        } catch (DaoException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reservation not found");
        }
    }

    @PutMapping("{id}/approve")
    public void approve(@PathVariable int id) {
        Person person = personDao.getPersonById(id);
        if (person.getIsApproved() != null) {
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

        emailService.send(person.getEmail(), "You were approved as a Volunteer", getApprovedMessage(person));
    }

    @PutMapping("{id}/decline")
    public void decline(@PathVariable int id) {
        Person person = personDao.getPersonById(id);
        if (person.getIsApproved() != null) {
            return;
        }

        person.setIsApproved(false);

        try {
            personDao.updatePerson(person);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Opps something went wrong.");
        }

        emailService.send(person.getEmail(), "Keep in touch", getDeclinedMessage(person));
    }

    private String getApprovedMessage(Person person) {
        String url = "http://localhost:5173/reset-password?token=" + person.getToken();

        return "Dear " + person.getFirstName() + " " + person.getLastName() + ",\n" +
                "\n" +
                "Congratulations! Your volunteer application with Paw Prints Pet Rescue has been approved. " +
                "We're thrilled to have you on board. " +
                "<a href='" + url + "'>Please click here</a> to set password and activate your volunteer account. " +
                "We'll be in touch soon with more details.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "Paw Prints Pet Rescue Team";
    }

    private String getDeclinedMessage(Person person) {
        return "Dear " + person.getFirstName() + " " + person.getLastName() + ",\n" +
                "\n" +
                "Thank you for your interest in volunteering with Paw Prints Pet Rescue." +
                "While we currently don't have a suitable opportunity available," +
                "we appreciate your enthusiasm. Please stay connected for future opportunities.\n" +
                "\n" +
                "Best regards,\n" +
                "\n" +
                "Paw Prints Pet Rescue Team";
    }
}
