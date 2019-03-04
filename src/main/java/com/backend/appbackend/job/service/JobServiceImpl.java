package com.backend.appbackend.job.service;

import com.backend.appbackend.job.entity.Job;
import com.backend.appbackend.job.exception.JobNotFoundException;
import com.backend.appbackend.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job getJob(Long id) throws JobNotFoundException {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isPresent()) {
            return job.get();
        }
        throw new JobNotFoundException("Job not found in database");
    }

    @Override
    public Job insertJob(Job job) {
        return this.jobRepository.save(job);
    }

    @Override
    public Job updateJob(Job job) throws JobNotFoundException {
        getJob(job.getId());
        return this.jobRepository.save(job);
    }

    @Override
    public void deleteJob(Long id) throws JobNotFoundException {
        getJob(id);
        this.jobRepository.deleteById(id);
    }
}
