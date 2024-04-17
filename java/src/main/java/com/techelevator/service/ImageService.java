package com.techelevator.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class ImageService implements ImageUploader {
    private final String DIR_PATH = "src/main/resources/photos";

    public String save(MultipartFile imageFile) throws IOException {
        if (imageFile == null || imageFile.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        String uniqueFileName = UUID.randomUUID() + "_" + imageFile.getOriginalFilename().replace(" ", "_");

        Path uploadPath = Path.of(DIR_PATH);
        Path filePath = uploadPath.resolve(uniqueFileName);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        Files.copy(imageFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

        return uniqueFileName;
    }

    public byte[] get(String imageName) throws IOException {
        if (imageName == null) {
            return null;
        }
        Path imagePath = Path.of(DIR_PATH, imageName);

        if (Files.exists(imagePath)) {
            return Files.readAllBytes(imagePath);
        }
        return null;
    }

    public void delete(String imageName) throws IOException {
        Path imagePath = Path.of(DIR_PATH, imageName);

        if (Files.exists(imagePath)) {
            Files.delete(imagePath);
        }
    }
}