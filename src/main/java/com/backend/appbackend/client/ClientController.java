package com.backend.appbackend.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Controller
public class ClientController {
    private ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping(value = "/clients/{id}")
    public Client getClient(@PathVariable String id) {
        try {
            return clientService.getClient(id);
        } catch (ClientNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @PostMapping(value = "/client")
    public ResponseEntity<Object> insertClient(@RequestBody Client client) {
        clientService.insertClient(client);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(client.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(value = "/client")
    public ResponseEntity<Object> updateClient(@RequestBody Client client) {
        try {
            clientService.updateClient(client);
            return ResponseEntity.noContent().build();
        } catch (ClientNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

    @DeleteMapping(value = "/clients/{id}")
    public void deleteClient(@PathVariable String id) {
        try {
            clientService.deleteClient(id);
        } catch (ClientNotFoundException ex) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
        }
    }

}
