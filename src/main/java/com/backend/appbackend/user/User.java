package com.backend.appbackend.user;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class User {

    @Id
    private String id;

    @NotBlank
    @Size(max = 20, message = "Max length for field: \"Name\" is 20")
    private String name;

    @NotBlank
    @Size(max = 32, message = "Max length for field: \"Surname\" is 32")
    private String surname;

    @Email
    @Column(unique = true)
    @Size(max = 32, message = "Max length for field: \"Email\" is 32")
    private String email;

    @NotBlank
    @Size(max = 64, message = "Max length for field: \"Region\" is 64")
    private String region;

    @NotBlank
    @Size(min = 7, message = "Min length for field: \"Password\" is 7")
    private String password;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
