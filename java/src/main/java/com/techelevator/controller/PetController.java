package com.techelevator.controller;

import com.techelevator.dao.PetDao;
import com.techelevator.dao.PhotoDao;
import com.techelevator.exception.DaoException;
import com.techelevator.model.Pet;
import com.techelevator.model.Photo;
import com.techelevator.service.ImageUploader;
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
    private PhotoDao photoDao;
    private ImageUploader imageService;

    public PetController(PetDao petDao, PhotoDao photoDao, ImageUploader imageService) {
        this.petDao = petDao;
        this.photoDao = photoDao;
        this.imageService = imageService;
    }

    @GetMapping
    public List<Pet> getPets(@RequestParam(defaultValue = "true") boolean isAdopted) {
        return petDao.getPetsByAdopted(isAdopted);
    }

    @GetMapping("{id}")
    public Pet getPetById(@PathVariable int id) {
        Pet pet = petDao.getPetById(id);
        pet.setPhotos(photoDao.getByPetId(pet.getPetId()));
        return pet;
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
            @RequestParam(required = false) MultipartFile mainPhoto,
            @RequestParam(required = false) MultipartFile[] photos
    ) {
        String photoName;
        try {
            photoName = imageService.save(mainPhoto);
        } catch (Exception e) {
            photoName = getPhotoPlaceholder(speciesId);
        }

        Pet pet = new Pet();
        pet.setMainPhoto(photoName);
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
            pet = petDao.createPet(pet);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet could not created");
        }

        for (MultipartFile multipartFile : photos) {
            String imageName;
            try {
                imageName = imageService.save(multipartFile);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
            Photo photo = new Photo(0, imageName, pet.getPetId());
            pet.addPhoto(photoDao.createPhoto(photo));
        }

        return pet;
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
            @RequestParam(required = false) MultipartFile mainPhoto,
            @RequestParam(required = false) MultipartFile[] photos
    ) {
        Pet pet = petDao.getPetById(id);

        try {
            if (mainPhoto != null && !mainPhoto.isEmpty()) {
                pet.setMainPhoto(imageService.save(mainPhoto));
            }
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }

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

        for (MultipartFile multipartFile : photos) {
            String imageName;
            try {
                imageName = imageService.save(multipartFile);
            } catch (Exception e) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
            }
            Photo photo = new Photo(0, imageName, pet.getPetId());
            pet.addPhoto(photoDao.createPhoto(photo));
        }

        try {
            return petDao.updatePet(pet);
        } catch (DaoException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Pet Not Found");
        }
    }

    @GetMapping(value = "/photos/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody byte[] getImage(@PathVariable String fileName) throws IOException {
        return imageService.get(fileName);
    }

    @PutMapping("{id}/remove-main-photo")
    @ResponseStatus(HttpStatus.OK)
    public String removeMainPhoto(@PathVariable int id) {
        Pet pet = petDao.getPetById(id);
        if (pet.getMainPhoto().equals(getPhotoPlaceholder(pet.getSpeciesId()))) {
            return pet.getMainPhoto();
        }

        try {
            imageService.delete(pet.getMainPhoto());
        } catch (IOException e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        pet.setMainPhoto(getPhotoPlaceholder(pet.getSpeciesId()));
        petDao.updatePet(pet);
        return pet.getMainPhoto();
    }

    @PutMapping("remove-photo/{id}")
    @ResponseStatus(HttpStatus.OK)
    public List<Photo> removePhoto(@PathVariable int id) {
        Photo photo = photoDao.getById(id);
        try {
            imageService.delete(photo.getFileName());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        photoDao.deleteById(id);

        return photoDao.getByPetId(photo.getPetId());
    }

    private String getPhotoPlaceholder(int speciesId) {
        return "placeholder_" + speciesId + ".jpg";
    }
}
