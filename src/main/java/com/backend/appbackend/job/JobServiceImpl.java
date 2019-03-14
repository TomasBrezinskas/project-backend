package com.backend.appbackend.job;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.appbackend.user.UserException;
import com.backend.appbackend.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
    public void insertJob(Job job, String token) {
        String email = getEmailFromToken(token);
        try {
            job.setOrganizator(userService.findUserByEmail(email));
        } catch (UserException ex) {
            ex.printStackTrace();
        }
        jobRepository.save(job);
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
                ex.printStackTrace();
            }
        }
        return filteredActiveJobs;
    }

    private List<Job> sortJobsByDate() {
        Sort sort = new Sort(Sort.Direction.ASC, "date");
        return jobRepository.findAll(sort);
    }

    private DecodedJWT getDecodedToken(String token) {
        //#TODO jei bad request tai programa luzta tai not that great
        String[] tokenParts = token.split(" ");
        token = tokenParts[1];
        return JWT.decode(token);
    }

    private String getEmailFromToken(String token) {
        DecodedJWT decodedToken = getDecodedToken(token);
        return decodedToken.getClaim("sub").asString();
    }
}
