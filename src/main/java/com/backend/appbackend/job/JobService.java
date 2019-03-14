package com.backend.appbackend.job;

import com.backend.appbackend.user.UserException;

import java.util.List;

public interface JobService {
    Job getJob(String id) throws JobNotFoundException;

    void insertJob(Job job, String token);

    Job updateJob(Job job) throws JobNotFoundException;

    void deleteJob(String id) throws JobNotFoundException;

    List<Job> fetchFutureJobsSortedByDate();

    List<Job> fetchAllJobs();

    List<Job> fetchNotActiveJobs();

    void insertParticipant(String token, String id) throws UserException, JobNotFoundException, TeamIsFullException;
}
