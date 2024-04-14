package com.techelevator.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageUploader {
    String save(MultipartFile imageFile) throws IOException;

    byte[] get(String imageName) throws IOException;

    void delete(String imageName) throws IOException;
}
