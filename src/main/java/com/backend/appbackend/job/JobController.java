package com.backend.appbackend.job;

import com.backend.appbackend.user.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class JobController {

    private JobService jobService;

    @Autowired
    public JobController(JobService jobService) {
        this.jobService = jobService;
    }

    @GetMapping(value = "/jobs/{id}")
    public Job getJob(@PathVariable String id) {
        try {
            return jobService.getJob(id);
        } catch (JobNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping(value = "/job")
    public ResponseEntity<Object> insertJob(@Valid @RequestBody Job job) {
        jobService.insertJob(job);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(job.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/job")
    public ResponseEntity<Object> updateJob(@RequestBody Job job) {
        try {
            jobService.updateJob(job);
            return ResponseEntity.noContent().build();
        } catch (JobNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping(value = "/jobs/{id}")
    public void deleteJob(@PathVariable String id) {
        try {
            jobService.deleteJob(id);
        } catch (JobNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @RequestMapping(value = "/jobs/all")
    public List<Job> fetchActiveSortedJobs() {
        return jobService.fetchFutureJobsSortedByDate();
    }

    @RequestMapping(value = "/jobs/allnosort")
    public List<Job> fetchAllJobs() {
        return jobService.fetchAllJobs();
    }

    @PostMapping(value = "/job/join")
    public void insertParticipant(@RequestHeader("Authorization") String token, @RequestBody String id) {
        try {
            jobService.insertParticipant(token, id);
        } catch (TeamIsFullException | JobNotFoundException | UserException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }
}
