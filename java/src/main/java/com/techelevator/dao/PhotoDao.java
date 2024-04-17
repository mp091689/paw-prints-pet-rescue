package com.techelevator.dao;

import com.techelevator.model.Photo;

import java.util.List;

public interface PhotoDao {
    List<Photo> getByPetId(int petId);

    Photo getById(int id);

    Photo createPhoto(Photo photo);

    void deleteById(int id);
}
