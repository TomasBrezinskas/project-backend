package com.backend.appbackend.job;

import java.util.List;

public interface JobService {
    Job getJob(String id) throws JobNotFoundException;

    void insertJob(Job job);

    void updateJob(Job job) throws JobNotFoundException;

    void deleteJob(String id) throws JobNotFoundException;

    List<Job> fetchFutureJobsSortedByDate();

    List<Job> fetchAllJobs();

    List<Job> fetchNotActiveJobs();
}
