package com.backend.appbackend.job;

import com.backend.appbackend.user.User;

import java.util.List;

public class JobResponse {
    private String id;
    private String date;
    private String idea;
    private String organisation;
    private String region;
    private String category;
    private String email;
    private String contactName;
    private String website;
    private String phone;
    private String description;
    private User organizator;
    private List<User> team;
    private boolean approved;

    private boolean canceled;

    private boolean isUserInTeam = false;

    public JobResponse() {
    }

    public JobResponse(String id, String date, String idea, String organisation, String region, String category, String email, String contactName, String website, String phone, String description, User organizator, List<User> team, boolean approved, boolean canceled) {
        this.id = id;
        this.date = date;
        this.idea = idea;
        this.organisation = organisation;
        this.region = region;
        this.category = category;
        this.email = email;
        this.contactName = contactName;
        this.website = website;
        this.phone = phone;
        this.description = description;
        this.organizator = organizator;
        this.team = team;
        this.approved = approved;
        this.canceled = canceled;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceledTrue() {
        this.canceled = true;
    }

    public boolean getApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public boolean getIsUserInTeam() {
        return isUserInTeam;
    }

    public void setUserInTeamTrue() {
        this.isUserInTeam = true;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public User getOrganizator() {
        return organizator;
    }

    public void setOrganizator(User organizator) {
        this.organizator = organizator;
    }

    public List<User> getTeam() {
        return team;
    }

    public void setTeam(List<User> team) {
        this.team = team;
    }
}
