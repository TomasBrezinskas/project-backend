package com.backend.appbackend.user;

public interface UserService {
    User getUser(String id) throws UserException;

    User insertUser(User user);

    User updateUser(User user) throws UserException;

    User signUpUser(User user) throws UserException;

    void deleteUser(String id) throws UserException;
}
