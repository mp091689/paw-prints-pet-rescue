package com.techelevator.controller;

import com.techelevator.dao.PersonDao;
import com.techelevator.model.Person;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
