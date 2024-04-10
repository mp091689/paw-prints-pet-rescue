package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Pet;
import com.techelevator.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("pets")
public class PetController {
    private PetDao petDao;
    private ImageService imageService;

    public PetController(PetDao petDao, ImageService imageService) {
        this.petDao = petDao;
        this.imageService = imageService;
    }

    @GetMapping
    public List<Pet> getPets(@RequestParam(defaultValue = "true") boolean isAdopted) {
        return petDao.getPetsByAdopted(isAdopted);
    }

    @GetMapping("{id}")
    public Pet getPetById(@PathVariable int id) {
        return petDao.getPetById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Pet createPet(
            @RequestParam String name,
            @RequestParam int speciesId,
            @RequestParam String gender,
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

        try {
            pet.setMainPhoto(imageService.saveToStorage(avatar));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

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
            @RequestParam String gender,
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
        Pet pet = petDao.getPetById(id);
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

        if (avatar != null) {
            try {
                pet.setMainPhoto(imageService.saveToStorage(avatar));
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
        }

        try {
            return petDao.updatePet(pet);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet Not Found");
        }
    }

    @GetMapping(value = "{id}/main-photo", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable int id) throws IOException {
        Pet pet = petDao.getPetById(id);
        if (pet == null) {
            return new byte[0];
        }
        return imageService.get(pet.getMainPhoto());
    }
}
