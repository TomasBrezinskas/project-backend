package com.backend.appbackend.job;

import com.backend.appbackend.job.Job;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {
    Job findJobByid(String id);
}
