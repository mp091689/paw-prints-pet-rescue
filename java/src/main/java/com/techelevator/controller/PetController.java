package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.model.Pet;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
    public List<Pet> getPets(){
        return petDao.getPets();
    }

    @PostMapping
    public String addPet(
            @RequestParam("petId") int petId,
            @RequestParam("name") String name,
            @RequestParam("avatar") MultipartFile file
    ) {
        return "success";
    }
}
