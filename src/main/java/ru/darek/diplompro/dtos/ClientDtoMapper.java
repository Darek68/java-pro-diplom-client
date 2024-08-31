package ru.darek.diplompro.dtos;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.darek.diplompro.entities.Client;

@Service
@RequiredArgsConstructor
public class ClientDtoMapper {
    public ClientInfoDto clientToDto(Client client){
        return ClientInfoDto.builder()
                .id(client.getId())
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .clientStatus(String.valueOf(client.getClientStatus()))
                .isAdmin(client.isAdmin())
                .comment(client.getComment())
                .build();
    }
}
