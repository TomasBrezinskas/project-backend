package com.backend.appbackend.story;

import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Document
public class StoryRegistration {
    @Id
    @GeneratedValue
    private String id;

    @NotBlank
    @Size(max = 1024, message = "Max length for field: \"Description\" is 1024")
    private String description;

    @NotBlank
    private String idea;

    private List<String> images;

    private boolean hasImages = false;

    public StoryRegistration(@NotBlank @Size(max = 1024, message = "Max length for field: \"Description\" is 1024") String description, @NotBlank String idea, List<String> images, boolean hasImages) {
        this.description = description;
        this.idea = idea;
        this.images = images;
        this.hasImages = hasImages;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIdea() {
        return idea;
    }

    public void setIdea(String idea) {
        this.idea = idea;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public boolean isHasImages() {
        return hasImages;
    }

    public void setHasImages(boolean hasImages) {
        this.hasImages = hasImages;
    }

    public void setHasImagesToTrue() {
        this.hasImages = true;
    }
}
