package com.techelevator.model;

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

    public Pet(int petId,
               int speciesId,
               String name,
               int age,
               boolean hasSpecialNeed,
               String color,
               String breed,
               String size,
               String gender,
               boolean isFixed,
               boolean isAdopted,
               String description
    ) {
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

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getSpeciesId() {
        return speciesId;
    }

    public void setSpeciesId(int speciesId) {
        this.speciesId = speciesId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean getHasSpecialNeed() {
        return hasSpecialNeed;
    }

    public void setHasSpecialNeed(boolean hasSpecialNeed) {
        this.hasSpecialNeed = hasSpecialNeed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public boolean getIsFixed() {
        return isFixed;
    }

    public void setIsFixed(boolean isAdopted) {
        this.isAdopted = isAdopted;
    }

    public boolean getIsAdopted() {
        return isAdopted;
    }

    public void setIsAdopted(boolean isAdopted) {
        this.isAdopted = isAdopted;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}