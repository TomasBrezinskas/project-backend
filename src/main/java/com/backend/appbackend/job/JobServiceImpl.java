package com.backend.appbackend.job;

import com.backend.appbackend.user.UserException;
import com.backend.appbackend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.backend.appbackend.job.JobUtils.DATE_FORMAT;
import static com.backend.appbackend.security.SecurityUtils.getEmailFromToken;

@Service
public class JobServiceImpl implements JobService {

    private JobRepository jobRepository;
    private UserService userService;

    @Autowired
    public JobServiceImpl(JobRepository jobRepository, UserService userService) {
        this.jobRepository = jobRepository;
        this.userService = userService;
    }

    @Override
    public Job getJob(String id) throws JobNotFoundException {
        return jobRepository.findById(id).orElseThrow(() -> new JobNotFoundException("Job not found in database"));
    }

    @Override
    public void insertJob(Job job, String token) throws JobIdeaAlreadyExistsException {
        String email = getEmailFromToken(token);
        try {
            job.setOrganizator(userService.findUserByEmail(email));
        } catch (UserException ex) {
            ex.printStackTrace();
        }
        if (jobRepository.findJobByIdea(job.getIdea()) != null) {
            throw new JobIdeaAlreadyExistsException("This Idea already exists in the database.");
        }
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
    public List<Job> fetchNotApprovedJobs() {
        return filterActiveJobs(false);
    }

    @Override
    public List<JobResponse> fetchFutureJobsSortedByDate(String token) {
        List<JobResponse> jobResponses = convertJob(filterActiveJobs(true));

        if (token != null) {
            try {
                String email = getEmailFromToken(token);
                for (JobResponse jobResponse : jobResponses) {
                    if (jobResponse.getTeam().contains(userService.findUserByEmail(email)) || jobResponse.getOrganizator().equals(userService.findUserByEmail(email))) {
                        jobResponse.setUserInTeamTrue();
                    }
                }
            } catch (UserException ex) {
                ex.printStackTrace();
            }
        }
        return jobResponses;
    }

    @Override
    public List<Job> fetchAllJobs() {
        return jobRepository.findAll();
    }

    public List<Job> fetchNotActiveJobs() {
        return getFilteredNotActiveJobs();
    }

    @Override
    public void insertParticipant(String token, String id) throws UserException, JobNotFoundException, TeamIsFullException {
        String email = getEmailFromToken(token);
        Job job = getJob(id);
        if (job.getTeam().contains(userService.findUserByEmail(email))) {
            throw new UserException("User is already participating in this job.");
        }
        if (job.getOrganizator().getEmail().equals(email)) {
            throw new UserException("User is organizing this job already.");
        }
        if (job.getTeam().size() >= 14) {
            throw new TeamIsFullException("Team is full");
        }
        job.getTeam().add(userService.findUserByEmail(email));
        updateJob(job);
    }

    @Override
    public void cancelParticipant(String token, String id) throws UserException, JobNotFoundException {
        String email = getEmailFromToken(token);
        Job job = getJob(id);
        if (!(job.getTeam().contains(userService.findUserByEmail(email)))) {
            throw new UserException("User is not participating in this job.");
        }
        if (job.getOrganizator().getEmail().equals(email)) {
            throw new UserException("User cannot cancel as he is organizing this job.");
        }
        job.getTeam().remove(userService.findUserByEmail(email));
        updateJob(job);
    }

    @Override
    public void approveJob(String id) throws JobNotFoundException {
        Job job = getJob(id);
        job.setApprovedTrue();
        updateJob(job);
    }

    @Override
    public void cancelJob(String id) throws JobNotFoundException {
        Job job = getJob(id);
        job.setCanceledTrue();
        updateJob(job);
    }

    private List<Job> filterActiveJobs(boolean status) {
        List<Job> sortedJobs = sortJobsByDate();
        Date date = new Date();
        Date jobDate;

        List<Job> filteredActiveJobs = new ArrayList<>();
        for (Job sortedJob: sortedJobs) {
            try {
                jobDate = DATE_FORMAT.parse(sortedJob.getDate());
                if ((jobDate.after(date) || jobDate.equals(date)) && sortedJob.getApproved() == status && !sortedJob.getCanceled()) {
                    filteredActiveJobs.add(sortedJob);
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

        for (Job job: jobs) {
            try {
                jobDate = DATE_FORMAT.parse(job.getDate());
                if (jobDate.before(date) && !job.getCanceled()) {
                    filteredNotActiveJobs.add(job);
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

    private List<JobResponse> convertJob(List<Job> jobs) {
        List<JobResponse> jobResponses = new ArrayList<>();
        for (Job job : jobs) {
            JobResponse jobResponse = new JobResponse(
                    job.getId(),
                    job.getDate(),
                    job.getIdea(),
                    job.getOrganisation(),
                    job.getRegion(),
                    job.getCategory(),
                    job.getEmail(),
                    job.getContactName(),
                    job.getWebsite(),
                    job.getPhone(),
                    job.getDescription(),
                    job.getOrganizator(),
                    job.getTeam(),
                    job.getApproved(),
                    job.getCanceled()
            );
            jobResponses.add(jobResponse);
        }
        return jobResponses;
    }
}
