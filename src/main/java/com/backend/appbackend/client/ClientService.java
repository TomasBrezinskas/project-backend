package com.backend.appbackend.client;

public interface ClientService {
    Client getClient(String id) throws ClientNotFoundException;

    Client insertClient(Client client);

    Client updateClient(Client client) throws ClientNotFoundException;

    void deleteClient(String id) throws ClientNotFoundException;
}
