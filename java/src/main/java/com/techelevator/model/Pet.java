package com.techelevator.model;

import java.util.Objects;

public class Pet {

    private int petId;
    private int speciesId;
    private String name;
    private int age;
    private boolean hasSpecialNeed;

    private String color;
    private String breed;

    private String size;
    private String gender;
    private boolean isFixed;
    private boolean isAdopted;
    private String description;

    public Pet() {

    }


    public void setPetId(int petId) {
        this.petId = petId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }
    public void setName(String name) {
        this.name = name;
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

    public void setSize(String size) {
        this.size = size;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void setIsFixed(boolean isAdopted) {
        this.isAdopted = isAdopted;
    }
    public void setIsAdopted(boolean isAdopted) {
        this.isAdopted = isAdopted;
    }

    public int getPetId() {
        return petId;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public String getName() {
        return name;
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
    public boolean getIsFixed() {
        return isFixed;
    }

    public boolean getIsAdopted() {
        return isAdopted;
    }

    public String getDescription() {
        return description;
    }
//
//    public Pets() {
//    }

    public Pet(int petId, int speciesId, String name, int age, boolean hasSpecialNeed,
                String color, String breed, String size,
                String gender, boolean isFixed, boolean isAdopted, String description) {
        this.petId = petId;
        this.speciesId = speciesId;
        this.name = name;
        this.age = age;
        this.hasSpecialNeed = hasSpecialNeed;
        this.color = color;
        this.breed = breed;
        this.size = size;
        this.gender = gender;
        this.description = description;
        this.isFixed = isFixed;
        this.isAdopted = isAdopted;
    }
}