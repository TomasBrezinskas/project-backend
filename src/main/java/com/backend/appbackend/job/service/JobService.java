package com.backend.appbackend.job.service;

import com.backend.appbackend.job.entity.Job;
import com.backend.appbackend.job.exception.JobNotFoundException;

public interface JobService {
    Job getJob(Long id) throws JobNotFoundException;

    Job insertJob(Job job);

    Job updateJob(Job job) throws JobNotFoundException;

    void deleteJob(Long id) throws JobNotFoundException;

    /*List<Job> getAndSortActiveJobs();*/
}
