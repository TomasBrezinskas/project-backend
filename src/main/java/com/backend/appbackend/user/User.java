package com.backend.appbackend.user;

import com.backend.appbackend.job.Job;
import org.apache.commons.lang3.builder.EqualsBuilder;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

public class User {

    @Id
    private String id;

    @NotBlank
    @Size(max = 32, message = "Max length for field: \"Name\" is 32")
    private String name;

    @NotBlank
    @Size(max = 64, message = "Max length for field: \"Surname\" is 64")
    private String surname;

    @Email
    @Column(unique = true)
    @Size(max = 128, message = "Max length for field: \"Email\" is 128")
    private String email;

    @NotBlank
    @Size(min = 7, message = "Min length for field: \"Password\" is 7")
    private String password;

    private String role;

    private List<String> attendedJobs;

    public User() {
        this.role = "user";
    }

    public List<String> getAttendedJobs() {
        return attendedJobs;
    }

    public void setAttendedJobs(List<String> attendedJobs) {
        this.attendedJobs = attendedJobs;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(id, user.id)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new org.apache.commons.lang3.builder.HashCodeBuilder(17, 37)
                .append(id)
                .toHashCode();
    }
}
