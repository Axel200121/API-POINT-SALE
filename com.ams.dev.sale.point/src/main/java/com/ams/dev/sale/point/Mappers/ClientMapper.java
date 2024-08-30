package com.ams.dev.sale.point.Mappers;

import com.ams.dev.sale.point.Dtos.ClientDto;
import com.ams.dev.sale.point.Entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    public ClientDto toDto(Client client){
        if (client == null) return null;

        ClientDto clientDto = new ClientDto();
        clientDto.setId(client.getId());
        clientDto.setName(client.getName());
        clientDto.setLastName(client.getLastName());
        clientDto.setPhone(client.getPhone());
        clientDto.setAddress(client.getAddress());
        clientDto.setEmail(client.getEmail());
        clientDto.setCreatedAt(client.getCreatedAt());
        clientDto.setUpdatedAt(client.getUpdatedAt());
        return clientDto;
    }

    public Client toEntity(ClientDto clientDto){
        if (clientDto == null) return null;
        Client client = new Client();
        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setLastName(clientDto.getLastName());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setEmail(clientDto.getEmail());
        client.setCreatedAt(clientDto.getCreatedAt());
        client.setUpdatedAt(clientDto.getUpdatedAt());
        return client;
    }
}
