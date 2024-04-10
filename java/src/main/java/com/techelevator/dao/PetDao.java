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
    List<Pet> getPetsByAdopted(boolean isAdopted);

    Pet getPetById(int petId);

    Pet createPet(Pet aPet);

    Pet updatePet(Pet aPet);
}

