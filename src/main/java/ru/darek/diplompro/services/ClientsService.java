package ru.darek.diplompro.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.darek.diplompro.controllers.ClientController;
import ru.darek.diplompro.entities.Client;
import ru.darek.diplompro.errors.ResourceNotFoundException;
import ru.darek.diplompro.repository.ClientsRepository;

@Service
@RequiredArgsConstructor
public class ClientsService {
    private static final Logger logger = LoggerFactory.getLogger(ClientsService.class.getName());
    private final ClientsRepository clientsRepository;

    public Client getClientInfoById(Long id) {
        logger.info("getClientInfoById id = {}  ",id);
        return clientsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Клиент с id = " + id + " не найден"));
    }
}
