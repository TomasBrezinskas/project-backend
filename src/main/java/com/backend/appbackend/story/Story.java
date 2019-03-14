package com.backend.appbackend.story;

import com.backend.appbackend.job.Job;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document
public class Story {

    @Id
    @GeneratedValue
    private String id;

    @NotBlank
    @Size(max = 1024, message = "Max length for field: \"Description\" is 1024")
    private String description;

    @NotBlank
    private Job job;

    public Story() {
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
