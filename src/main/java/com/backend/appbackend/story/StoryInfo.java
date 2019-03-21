package com.backend.appbackend.story;

import com.backend.appbackend.job.Job;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.validation.constraints.NotBlank;

@Inheritance
public class StoryInfo {

    @Id
    @GeneratedValue
    private String id;

    /*@NotBlank
    private Job job;*/

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

  /*  public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }*/
}
