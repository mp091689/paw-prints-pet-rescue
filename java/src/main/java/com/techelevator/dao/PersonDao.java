package com.techelevator.dao;

import com.techelevator.model.Person;

import java.util.List;

public interface PersonDao {
    List<Person> getPeople();
    Person getPersonById(int personId);
    Person getPersonByEmail(String email);
    Person createPerson(Person individual);
    Person updatePerson(Person updateIndividual);
}
