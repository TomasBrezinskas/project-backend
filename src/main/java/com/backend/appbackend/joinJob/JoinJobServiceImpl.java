package com.backend.appbackend.joinJob;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.backend.appbackend.job.Job;
import com.backend.appbackend.job.JobNotFoundException;
import com.backend.appbackend.job.JobRepository;
import com.backend.appbackend.user.UserException;
import com.backend.appbackend.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JoinJobServiceImpl implements JoinJobService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JobRepository jobRepository;

    @Override
    public void insertParticipant(String token, String id) throws UserException, JobNotFoundException, TeamIsFullException {

        String email = getEmailFromToken(token);
        Job job = jobRepository.findJobById(id);

        if (userRepository.findUserByEmail(email) == null) {
            throw new UserException("Email " + email + " not found in the database.");
        }

        if (jobRepository.findJobById(id) == null) {
            throw new JobNotFoundException("Job not found in the database.");
        }

        if (job.getParticipants().size() == 15) {
            throw new TeamIsFullException("Team is full");
        } else {
            job.setParticipant(userRepository.findUserByEmail(email));
        }
    }

    private DecodedJWT getDecodedToken(String token) {
        String[] tokenParts = token.split(" ");
        token = tokenParts[1];
        return JWT.decode(token);
    }

    private String getEmailFromToken(String token) {
        DecodedJWT decodedToken = getDecodedToken(token);
        return decodedToken.getClaim("sub").asString();
    }
}
