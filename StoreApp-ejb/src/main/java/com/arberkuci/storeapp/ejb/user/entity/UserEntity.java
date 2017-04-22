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

    private String name;

    private String surName;

    public UserEntity() {

    }

    public UserEntity(Long id, String name, String surName) {
        this.id = id;
        this.name = name;
        this.surName = surName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String toString() {
        return new StringBuilder().append("{ id = ").append(this.getId()).append(", ")
                .append("name = ").append(this.getSurName()).append(", ").append("surname = ").
                        append(this.getSurName()).append(" }").toString();
    }

}
