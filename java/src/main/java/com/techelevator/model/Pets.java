package com.techelevator.model;

import java.util.Objects;

public class Pets {

    private int petId;
    private String species;

    private int age;
    private boolean hasSpecialNeed;

    private String color;
    private String breed;

    private String size;
    private String gender;
    private boolean isAdopted;
    private String description;


    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setHasSpecialNeed(boolean hasSpecialNeed) {
        this.hasSpecialNeed = hasSpecialNeed;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public void setIsAdopted(boolean isAdopted) {
        this.isAdopted = isAdopted;
    }

    public int getPetId() {
        return petId;
    }

    public String getSpecies() {
        return species;
    }

    public int getAge() {
        return age;
    }

    public boolean getHasSpecialNeed() {
        return hasSpecialNeed;
    }

    public String getColor() {
        return color;
    }

    public String getBreed() {
        return breed;
    }

    public String getSize() {
        return size;
    }

    public String getGender() {
        return gender;
    }

    public boolean getIsAdopted() {
        return isAdopted;
    }

    public text getDescription() {
        return description;
    }

    public Pets() {
    }

    public Pets(int petId, String species, int age, boolean hasSpecialNeed,
                String color, String breed, String size,
                String gender, boolean isAdopted, text description) {
        this.petId = petId;
        this.species = species;
        this.age = age;
        this.hasSpecialNeed = hasSpecialNeed;
        this.color = color;
        this.breed = breed;
        this.size = size;
        this.gender = gender;
        this.description = description;
        this.isAdopted = isAdopted;
    }
}