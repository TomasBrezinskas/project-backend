package com.backend.appbackend.client.service;

import com.backend.appbackend.client.entity.Client;
import com.backend.appbackend.client.exception.ClientNotFoundException;
import com.backend.appbackend.client.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    private ClientRepository clientRepository;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public Client getClient(String id) throws ClientNotFoundException {
        //#TODO sake augustinas galima oneliner padaryt, paupgradinom i twoliner
        Optional<Client> client = clientRepository.findById(id);
        return client.orElseThrow(() -> new ClientNotFoundException("Client not found in database"));
    }

    @Override
    public Client insertClient(Client client) {
        return this.clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) throws ClientNotFoundException {
        getClient(client.getId());
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(String id) throws ClientNotFoundException {
        getClient(id);
        clientRepository.deleteById(id);
    }
}
