package com.backend.appbackend.story;

import com.backend.appbackend.job.Job;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class StoryResponse {

    private String id;

    @NotBlank
    @Size(max = 1024, message = "Max length for field: \"Description\" is 1024")
    private String description;

    @NotBlank
    private Job job;

    private boolean hasImages;

    public StoryResponse(String id, @NotBlank @Size(max = 1024, message = "Max length for field: \"Description\" is 1024") String description, @NotBlank Job job, boolean hasImages) {
        this.id = id;
        this.description = description;
        this.job = job;
        this.hasImages = hasImages;
    }

    public boolean isHasImages() {
        return hasImages;
    }

    public void setHasImages(boolean hasImages) {
        this.hasImages = hasImages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
