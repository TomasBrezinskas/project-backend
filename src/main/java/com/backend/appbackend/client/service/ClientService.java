package com.backend.appbackend.client.service;

import com.backend.appbackend.client.entity.Client;
import com.backend.appbackend.client.exception.ClientNotFoundException;

public interface ClientService {
    Client getClient(String id) throws ClientNotFoundException;

    Client insertClient(Client client);

    Client updateClient(Client client) throws ClientNotFoundException;

    void deleteClient(String id) throws ClientNotFoundException;
}
