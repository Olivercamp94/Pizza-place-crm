package com.pizza.crm.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pizza.crm.model.Client;
import com.pizza.crm.repository.ClientRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService  {

    // Lista simulando um banco de dados temporário
    
    private final ClientRepository clientRepository;
    
    public ClientService(ClientRepository clientRepository) {
    	this.clientRepository = clientRepository;
    }
    
    // CREATE - Adicionar um novo cliente
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    // READ - Buscar um cliente pelo e-mail
    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }
    
    // READ - Buscar cliente por ID
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }
    
    // UPDATE - Atualizar dados de um cliente
    public Client updateClient(Long id, Client updatedClient) {
    	Optional<Client> clientExists = clientRepository.findById(id);
    	if(clientExists.isPresent()) {
    		Client client = clientExists.get();
    		client.setEmail(updatedClient.getEmail());
    		client.setName(updatedClient.getName());
    		client.setPhone(updatedClient.getPhone());
    		return clientRepository.save(client);
    	}else {
    		throw new RuntimeException("client id: " + id + " not found.");
    	}
    }

//    // DELETE - Remover um cliente
    @Transactional
    public boolean deleteClient(Long id) {
    	if(clientRepository.existsById(id)) {
    		clientRepository.deleteById(id);
    		return true;
    	}else {
    		throw new RuntimeException("Client id: " + id + " not found.");
    	}
    }
}
