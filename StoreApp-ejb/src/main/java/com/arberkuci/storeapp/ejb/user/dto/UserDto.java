package com.arberkuci.storeapp.ejb.user.dto;

public class UserDto {

    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private Double points;

    //TODO Temporary field -> it has to do nothing with business logic!
    private boolean isFromCache;

    public UserDto() {
    }

    public UserDto(Long id, String firstName, String surName, String userName, Double points, boolean isFromCache) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = surName;
        this.userName = userName;
        this.points = points;
        this.isFromCache = isFromCache;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public boolean isFromCache() {
        return isFromCache;
    }

    public void setFromCache(boolean fromCache) {
        this.isFromCache = fromCache;
    }

    public String toString() {
        return new StringBuilder().append("{ id = ").append(this.getId()).append(", ").append("firstName = ").
                append(this.getLastName()).append(", ").append("lastName = ").
                append(this.getLastName()).append("userName = ").append(this.getUserName()).append("points = ").
                append(this.getPoints()).append(", isFromCache = ").append(this.isFromCache()).append(" }").toString();
    }

}
