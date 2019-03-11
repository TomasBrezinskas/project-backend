package com.backend.appbackend.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "/users/{id}")
    public User getUser(@PathVariable String id) {
        try {
            return userService.getUser(id);
        } catch (UserException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping(value = "/user")
    public ResponseEntity<Object> insertUser(@RequestBody User user) {
        userService.insertUser(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/users")
    public ResponseEntity<Object> updateUser(@RequestBody User user) {
        try {
            userService.updateUser(user);
            return ResponseEntity.noContent().build();
        } catch (UserException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping(value = "/users/{id}")
    public void deleteUser(@PathVariable String id) {
        try {
            userService.deleteUser(id);
        } catch (UserException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping("/sign-up")
    public void signUpUser(@Valid @RequestBody User user) {
        try {
            userService.signUpUser(user);
        } catch (UserException ex) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
        }
    }

}
