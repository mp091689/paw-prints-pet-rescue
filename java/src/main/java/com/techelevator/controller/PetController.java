package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("pets")
public class PetController {
    private final PetDao petDao;

    public PetController(PetDao petDao) {
        this.petDao = petDao;
    }

    @GetMapping
    public List<Pet> getPets() {
        return petDao.getPets();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet createPet(
            @RequestParam String name,
            @RequestParam int speciesId,
            @RequestParam(required = false) String gender,
            @RequestParam String size,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) int age,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) boolean hasSpecialNeed,
            @RequestParam(required = false) boolean isFixed,
            @RequestParam(required = false) boolean isAdopted,
            @RequestParam(required = false) MultipartFile avatar
    ) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setAge(age);
        pet.setSpeciesId(speciesId);
        pet.setHasSpecialNeed(hasSpecialNeed);
        pet.setColor(color);
        pet.setBreed(breed);
        pet.setSize(size);
        pet.setGender(gender);
        pet.setIsFixed(isFixed);
        pet.setIsAdopted(isAdopted);
        pet.setDescription(description);

        // todo: upload file and set avatar variable to pet.avatar

        try {
            return petDao.createPet(pet);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet could not created");
        }
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public Pet updatePet(
            @PathVariable int id,
            @RequestParam String name,
            @RequestParam int speciesId,
            @RequestParam(required = false) String gender,
            @RequestParam String size,
            @RequestParam(required = false) String breed,
            @RequestParam(required = false) int age,
            @RequestParam(required = false) String color,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) boolean hasSpecialNeed,
            @RequestParam(required = false) boolean isFixed,
            @RequestParam(required = false) boolean isAdopted,
            @RequestParam(required = false) MultipartFile avatar
    ) {
        Pet pet = new Pet();
        pet.setPetId(id);
        pet.setName(name);
        pet.setAge(age);
        pet.setSpeciesId(speciesId);
        pet.setHasSpecialNeed(hasSpecialNeed);
        pet.setColor(color);
        pet.setBreed(breed);
        pet.setSize(size);
        pet.setGender(gender);
        pet.setIsFixed(isFixed);
        pet.setIsAdopted(isAdopted);
        pet.setDescription(description);

        // todo: upload file and set avatar variable to pet.avatar

        try {
            return petDao.updatePet(pet);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet Not Found");
        }
    }
}
