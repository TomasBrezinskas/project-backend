package com.backend.appbackend.job;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepository extends MongoRepository<Job, String> {
    Job findJobById(String id);

    Job findJobByEmail(String email);

    Job findJobByIdea(String idea);
}
