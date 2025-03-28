package com.pizza.crm.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pizza.crm.model.Client;

@Service
public class ClientService {

    // Lista simulando um banco de dados temporário
    private List<Client> clients = new ArrayList<>();

    // CREATE - Adicionar um novo cliente
    public Client addClient(Client client) {
        clients.add(client);
        return client;
    }

    // READ - Buscar todos os clientes
    public List<Client> getAllClients() {
        return clients;
    }

    // READ - Buscar um cliente pelo e-mail
    public Optional<Client> getClientByEmail(String email) {
        return clients.stream()
                .filter(client -> client.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }
    
    // READ - Buscar cliente por ID
    public Optional<Client> getClientById(Long id) {
        return clients.stream()
                .filter(client -> client.getId().equals(id))
                .findFirst();
    }
    
    // UPDATE - Atualizar dados de um cliente
    public Optional<Client> updateClient(Long id, Client updatedClient) {
        Optional<Client> existingClient = getClientById(id);

        existingClient.ifPresent(client -> {
            client.setName(updatedClient.getName());
            client.setPhone(updatedClient.getPhone());
        });
        
        return existingClient;
    }

    // DELETE - Remover um cliente
    public boolean deleteClient(Long id) {
        return clients.removeIf(client -> client.getId().equals(id));
    }
}
