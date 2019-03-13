package com.backend.appbackend.joinJob;

import com.backend.appbackend.job.JobNotFoundException;
import com.backend.appbackend.user.UserException;

public interface JoinJobService {
    void insertParticipant(String token, String id) throws UserException, JobNotFoundException, TeamIsFullException;
}
