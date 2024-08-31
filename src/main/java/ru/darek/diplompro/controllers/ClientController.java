package ru.darek.diplompro.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import ru.darek.diplompro.dtos.ClientDtoMapper;
import ru.darek.diplompro.dtos.ClientInfoDto;
import ru.darek.diplompro.entities.Client;
import ru.darek.diplompro.services.ClientsService;

@RestController
@RequestMapping("/api/v1/clients")
@RequiredArgsConstructor
public class ClientController {
    private static final Logger logger = LoggerFactory.getLogger(ClientController.class.getName());
    private final ClientsService clientsService;
    private final ClientDtoMapper clientDtoMapper;

    @GetMapping("/{id}")
    public ClientInfoDto getClientInfo(@PathVariable Long id,@RequestHeader Long requesterId) {
        logger.info("getClientInfo id = {}    requesterId = {}",id,requesterId);
        Client client = clientsService.getClientInfoById(id);
        return clientDtoMapper.clientToDto(client);
    }

    @GetMapping("/test")
    public String getClientInfo(@RequestHeader Long requesterId) {
        return "ID - запрасителя = " + requesterId;
    }

}
