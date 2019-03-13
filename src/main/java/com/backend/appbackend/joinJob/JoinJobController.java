package com.backend.appbackend.joinJob;

import com.backend.appbackend.job.JobNotFoundException;
import com.backend.appbackend.user.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin(origins = "*")
@RestController
public class JoinJobController {

    private JoinJobService joinJobService;

    @Autowired
    public JoinJobController(JoinJobService joinJobService) {
        this.joinJobService = joinJobService;
    }

    @PostMapping(value = "/job/join")
    public void insertParticipant(@RequestHeader("Authorization") String token, @RequestBody String id) {
        try {
            joinJobService.insertParticipant(token, id);
        } catch (UserException | JobNotFoundException | TeamIsFullException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }

    }
}
