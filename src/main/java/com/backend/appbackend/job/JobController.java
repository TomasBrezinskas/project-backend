package com.backend.appbackend.job;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/")
public class JobController {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private final JobRepository jobRepository;

    public JobController(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Job addNewJob(@RequestBody Job job) {
        log.info("saving job");
        return jobRepository.save(job);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<Job> getAllUsers() {
        log.info("Getting all users.");
        return jobRepository.findAll();
    }
}
