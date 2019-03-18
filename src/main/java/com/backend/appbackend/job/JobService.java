package com.backend.appbackend.job;

import com.backend.appbackend.user.UserException;

import java.util.List;

public interface JobService {
    Job getJob(String id) throws JobNotFoundException;

    void insertJob(Job job, String token) throws JobIdeaAlreadyExistsException;

    void updateJob(Job job) throws JobNotFoundException;

    void deleteJob(String id) throws JobNotFoundException;

    List<Job> fetchNotApprovedJobs();

    List<JobResponse> fetchFutureJobsSortedByDate(String token);

    List<Job> fetchAllJobs();

    List<Job> fetchNotActiveJobs();

    void insertParticipant(String token, String id) throws UserException, JobNotFoundException, TeamIsFullException;

    void cancelParticipant(String token, String id) throws UserException, JobNotFoundException;

    void approveJob(String id) throws JobNotFoundException;

    void cancelJob(String id) throws JobNotFoundException;
}
