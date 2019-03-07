package com.backend.appbackend.job;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Document
public class Job {

    @Id
    @GeneratedValue
    private String id;

    @NotBlank
    private String date;

    @NotBlank
    @Size(max = 256, message = "Max length for field: \"Idea\" is 256")
    private String idea;

    @NotBlank
    @Size(max = 128, message = "Max length for field: \"Organisation\" is 128")
    private String organisation;

    @NotBlank
    @Size(max = 40, message = "Max length for field: \"City\" is 40")
    private String city;

    @NotBlank
    @Size(max = 40, message = "Max length for field: \"Category\" is 40")
    private String category;

    @Email
    @Size(max = 50, message = "Max length for field: \"Email\" is 50")
    private String email;

    @NotBlank
    @Size(max = 64, message = "Max length for field: \"Contact Name\" is 64")
    private String contactName;

    @Size(max = 64, message = "Max length for field: \"Website\" is 64")
    private String website;

    @NotBlank
    private String phone;

    @NotBlank
    @Size(max = 1024, message = "Max length for field: \"Description\" is 1024")
    private String description;

    private List<String> hashtags;

    public Job() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public String getOrganisation() {
        return organisation;
    }

    public void setOrganisation(String organisation) {
        this.organisation = organisation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }
}
