package com.arberkuci.storeapp.ejb.user.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private String userName;

    private Double points;

    public UserEntity() {
    }

    public UserEntity(Long id, String firstName, String lastName, String userName, Double points) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.points = points;
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

    public Double getPoints(){return this.points;}

    public void setPoints(Double points){this.points = points;}

    public String toString() {
        return new StringBuilder().append("{ id = ").append(this.getId()).append(", ")
                .append("firstName = ").append(this.getLastName()).append(", ").append("lastName = ").
                        append(this.getLastName()).append("userName = ").
                        append(this.getUserName()).append(", ").append("points = ").append(this.getPoints()).
                        append(" }").toString();
    }

}
