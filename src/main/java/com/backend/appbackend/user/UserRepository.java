package com.backend.appbackend.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository()
public interface UserRepository extends MongoRepository<User, String> {
    User findUserByEmail(String email);
}
