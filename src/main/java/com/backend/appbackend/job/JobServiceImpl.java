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

import static com.backend.appbackend.job.JobUtils.DATE_FORMAT;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @Override
    public Job getJob(String id) throws JobNotFoundException {
        return jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found in database"));
    }

    @Override
    public void insertJob(Job job) {
        jobRepository.save(job);
    }

    @Override
    public void updateJob(Job job) throws JobNotFoundException {
        getJob(job.getId());
        jobRepository.save(job);
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

    public List<Job> fetchNotActiveJobs() {return getFilteredNotActiveJobs();}

    private List<Job> filterActiveJobs() {
        List<Job> sortedJobs = sortJobsByDate();
        Date date = new Date();
        Date jobDate;

        List<Job> filteredActiveJobs = new ArrayList<>();
        for (int i = 0; i < sortedJobs.size(); i++) {
            try {
                jobDate = DATE_FORMAT.parse(sortedJobs.get(i).getDate());
                if (jobDate.after(date) || jobDate.equals(date)) {
                    filteredActiveJobs.add(sortedJobs.get(i));
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return filteredActiveJobs;
    }

    private List<Job> getFilteredNotActiveJobs() {
        Date date = new Date();
        Date jobDate;
        List<Job> jobs = jobRepository.findAll();

        List<Job> filteredNotActiveJobs = new ArrayList<>();
        for (int i = 0; i < jobs.size(); i++) {
            try {
                jobDate = DATE_FORMAT.parse(jobs.get(i).getDate());
                if (jobDate.before(date)) {
                    filteredNotActiveJobs.add(jobs.get(i));
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        }
        return filteredNotActiveJobs;
    }

    private List<Job> sortJobsByDate() {
        Sort sort = new Sort(Sort.Direction.ASC, "date");
        return jobRepository.findAll(sort);
    }
}
