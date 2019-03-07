package com.backend.appbackend.user;

import com.backend.appbackend.user.User;
import com.backend.appbackend.user.UserNotFoundException;

public interface UserService {
    User getUser(String id) throws UserNotFoundException;

    User insertUser(User user);

    User updateUser(User user) throws UserNotFoundException;

    User signUpUser(User user) throws UserNotFoundException;

    void deleteUser(String id) throws UserNotFoundException;
}
