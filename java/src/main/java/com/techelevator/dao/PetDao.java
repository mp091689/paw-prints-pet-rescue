package com.techelevator.dao;

import com.techelevator.model.Pet;

import java.util.List;

public interface PetDao {
    /**
     * Get a list of all pets from the datastore.
     * The list is never null. It is empty if there are no pets in the datastore.
     *
     * @return all pets as a list of Pet objects
     */
    List<Pet> getPets();

    /**
     * Get a pet from the datastore that has the given id.
     * If the id is not found, return null.
     *
     * @param id the pet id to get from the datastore
     * @return a fully populated Pet object
     */
    Pet getPetsById(int id);

    Pet getPetsByUserId(int petId);

//    User createPet(RegisterPetDto pet);
}

