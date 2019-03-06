package com.backend.appbackend.client.repository;

import com.backend.appbackend.client.entity.Client;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientRepository extends MongoRepository<Client, String> {
    Client findClientById(String id);
}
