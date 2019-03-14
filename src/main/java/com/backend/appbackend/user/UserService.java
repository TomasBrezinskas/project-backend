package com.backend.appbackend.user;

public interface UserService {
    User getUser(String id) throws UserException;

    void insertUser(User user);

    void updateUser(User user) throws UserException;

    void signUpUser(User user) throws UserException;

    void deleteUser(String id) throws UserException;

    User findUserByEmail(String email) throws UserException;
}
