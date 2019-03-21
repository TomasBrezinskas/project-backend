package com.backend.appbackend.story;

import com.backend.appbackend.job.Job;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Document
public class Story {

    private String id;

    @NotBlank
    @Size(max = 1024, message = "Max length for field: \"Description\" is 1024")
    private String description;

    @NotBlank
    private Job job;

    private List<String> images;

    private boolean hasImages;

    public Story(String id, @NotBlank @Size(max = 1024, message = "Max length for field: \"Description\" is 1024") String description, @NotBlank Job job, List<String> images, boolean hasImages) {
        this.id = id;
        this.description = description;
        this.job = job;
        this.images = images;
        this.hasImages = hasImages;
    }

    public void setHasImagesToTrue() {
        this.hasImages = true;
    }

    public boolean isHasImages() {
        return hasImages;
    }

    public void setHasImages(boolean hasImages) {
        this.hasImages = hasImages;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }
}
