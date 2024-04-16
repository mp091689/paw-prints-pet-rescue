package com.techelevator.model;

public class Person {
    private int personId;
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private boolean isAvailableWeekdays;
    private boolean isAvailableWeekends;
    private String volunteeringInterest;
    private Boolean isApproved;
    private String token;

    public Person() {}

    public Person(int personId, int userId, String firstName, String lastName, String email, boolean isAvailableWeekdays, boolean isAvailableWeekends, String volunteeringInterest, boolean isApproved, String token) {
        this.personId = personId;
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.isAvailableWeekdays = isAvailableWeekdays;
        this.isAvailableWeekends = isAvailableWeekends;
        this.volunteeringInterest = volunteeringInterest;
        this.isApproved = isApproved;
        this.token = token;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAvailableWeekdays() {
        return isAvailableWeekdays;
    }

    public void setAvailableWeekdays(boolean availableWeekdays) {
        isAvailableWeekdays = availableWeekdays;
    }

    public boolean isAvailableWeekends() {
        return isAvailableWeekends;
    }

    public void setAvailableWeekends(boolean availableWeekends) {
        isAvailableWeekends = availableWeekends;
    }

    public String getVolunteeringInterest() {
        return volunteeringInterest;
    }

    public void setVolunteeringInterest(String volunteeringInterest) {
        this.volunteeringInterest = volunteeringInterest;
    }

    public Boolean getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Boolean isApproved) {
        this.isApproved = isApproved;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
