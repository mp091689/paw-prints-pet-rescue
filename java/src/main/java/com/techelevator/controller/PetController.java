package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Pet;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("pets")
public class PetController {
    private PetDao petDao;
    public PetController(PetDao petDao) {
        this.petDao = petDao;
    }
    @GetMapping
    public List<Pet> getPets(){
        return petDao.getPets();
    }


    @PostMapping
    public Pet createPet(@RequestBody Pet aPet) {
        try{
            Pet createdPet = petDao.createPet(aPet);
            return createdPet;
        }catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet could not created");
        }
    }

    @PutMapping("{id}")
    public Pet updatePet(@RequestBody Pet aPet, @PathVariable int id) {
        aPet.setPetId(id);
        try {
            Pet updatedPet = petDao.updatePet(aPet);
            return updatedPet;
        }
        catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet Not Found");
        }
    }
}
