package com.techelevator.model;

public class Photo {

    private int photoId;
    private String fileName;
    private int petId;

    public Photo(int photoId, String fileName, int petId) {
        this.photoId = photoId;
        this.fileName = fileName;
        this.petId = petId;
    }

    public Photo() {
    }

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }
}
