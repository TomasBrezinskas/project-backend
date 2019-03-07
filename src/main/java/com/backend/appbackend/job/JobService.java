package com.backend.appbackend.job;

import com.backend.appbackend.job.Job;
import com.backend.appbackend.job.JobNotFoundException;

import java.util.List;

public interface JobService {
    Job getJob(String id) throws JobNotFoundException;

    Job insertJob(Job job);

    Job updateJob(Job job) throws JobNotFoundException;

    void deleteJob(String id) throws JobNotFoundException;

    List<Job> fetchFutureJobsSortedByDate();

    List<Job> fetchAllJobs();
}
