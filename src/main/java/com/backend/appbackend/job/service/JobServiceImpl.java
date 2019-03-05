package com.backend.appbackend.job.service;

import com.backend.appbackend.job.entity.Job;
import com.backend.appbackend.job.exception.JobNotFoundException;
import com.backend.appbackend.job.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job getJob(String id) throws JobNotFoundException {
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
        return jobRepository.save(job);
    }

    @Override
    public void deleteJob(String id) throws JobNotFoundException {
        getJob(id);
        jobRepository.deleteById(id);
    }

    @Override
    public List<Job> getAndSortActiveJobs() {
        List<Job> sortedJobsByDate = sortJobsByDate();
        return sortActiveJobs(sortedJobsByDate);
    }

    private List<Job> sortActiveJobs(List<Job> sortedJobs) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date jobDate;

        List<Job> sortedActiveJobs = new ArrayList<>();
        for (int i = 0; i < sortedJobs.size(); i++){
            System.out.println(sortedJobs.get(i).getDate());
            try {
                jobDate = dateFormat.parse(sortedJobs.get(i).getDate());
                System.out.println("lyginam" + date + " su " + jobDate);
                if(jobDate.after(date) || jobDate.equals(date)){

                    sortedActiveJobs.add(sortedJobs.get(i));
                }
            } catch (ParseException ex) {
                //#TODO
            }
        }
        return sortedActiveJobs;
    }

    private List<Job> sortJobsByDate() {
        Sort sort = new Sort(Sort.Direction.ASC, "date");
        return jobRepository.findAll(sort);
    }

    public List<Job> getAllJobs() {
        return jobRepository.findAll();
    }
}
