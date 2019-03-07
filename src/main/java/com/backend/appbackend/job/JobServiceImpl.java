package com.backend.appbackend.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
        return job.orElseThrow(() -> new JobNotFoundException("Job not found in database"));
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
    public List<Job> fetchFutureJobsSortedByDate() {
        return filterActiveJobs();
    }

    @Override
    public List<Job> fetchAllJobs() {
        return jobRepository.findAll();
    }

    private List<Job> filterActiveJobs() {
        List<Job> sortedJobs = sortJobsByDate();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        Date jobDate;

        List<Job> filteredActiveJobs = new ArrayList<>();
        for (int i = 0; i < sortedJobs.size(); i++) {
            try {
                jobDate = dateFormat.parse(sortedJobs.get(i).getDate());
                if (jobDate.after(date) || jobDate.equals(date)) {
                    filteredActiveJobs.add(sortedJobs.get(i));
                }
            } catch (ParseException ex) {
                //#TODO
            }
        }
        return filteredActiveJobs;
    }

    private List<Job> sortJobsByDate() {
        Sort sort = new Sort(Sort.Direction.ASC, "date");
        return jobRepository.findAll(sort);
    }
}
