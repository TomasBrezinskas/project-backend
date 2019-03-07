package com.backend.appbackend.user.repository;

import com.backend.appbackend.user.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserById(String id);
    User findUserByEmail(String email);
}
