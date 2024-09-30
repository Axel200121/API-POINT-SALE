package com.ams.dev.sale.point.Services.impl;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.ClientDto;
import com.ams.dev.sale.point.Entities.Client;
import com.ams.dev.sale.point.Mappers.ClientMapper;
import com.ams.dev.sale.point.Repositories.ClientRepository;
import com.ams.dev.sale.point.Services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientMapper clientMapper;

    @Override
    public ApiResponseDto createClient(ClientDto clientDto) {
        Optional<Client> existingClient = clientRepository.findByEmail(clientDto.getEmail());
        if (existingClient.isPresent())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Hay un cliente con el mismo correo electronico",null);

        if (validateInput(clientDto.getName()) && validateInput(clientDto.getLastName()) && validateInput(clientDto.getPhone()) && validateInput(clientDto.getAddress()) && validateInput(clientDto.getEmail()))
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Faltaron campos por validar, verifica tus datos",null);

        Client clientSave = clientRepository.save(clientMapper.toEntity(clientDto));
        return new ApiResponseDto<>(HttpStatus.CREATED.value(),"Cliente registrado exitosamente",clientMapper.toDto(clientSave));
    }

    @Override
    public ApiResponseDto updateClient(String idClient, ClientDto clientDto) {
        Optional<Client> existingClient = clientRepository.findById(idClient);
        if (existingClient.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No se encontro el cliente en la BD",null);

        Client clientEdit = existingClient.get();
        clientEdit.setName(clientDto.getName());
        clientEdit.setLastName(clientDto.getLastName());
        clientEdit.setPhone(clientDto.getPhone());
        clientEdit.setAddress(clientDto.getAddress());
        clientEdit.setEmail(clientDto.getEmail());
        Client clientSave = clientRepository.save(clientEdit);

        return new ApiResponseDto<>(HttpStatus.OK.value(),"Cliente actualizado exitosamente",clientMapper.toDto(clientSave));
    }

    @Override
    public ApiResponseDto getClient(String id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), "No se encontro el cliente en la BD",null);

        Client clientBD = client.get();
        ClientDto clientDto = clientMapper.toDto(clientBD);
        return new ApiResponseDto<>(HttpStatus.OK.value(),"Cliente encontrado", clientDto);
    }

    @Override
    public ApiResponseDto getAllClients() {
        List<Client> clientList = clientRepository.findAll();
        if (clientList.isEmpty())
            return  new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No tienes clientes registrados",null);

        List<ClientDto> clientDtos = clientList.stream().map(clientMapper::toDto).collect(Collectors.toList());
        return new ApiResponseDto<>(HttpStatus.OK.value(),"Clientes registrados",clientDtos);
    }

    @Override
    public ApiResponseDto deleteClient(String idClient) {
        Optional<Client> client = clientRepository.findById(idClient);
        if (client.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), "No se encontro el cliente en la BD",null);

        clientRepository.deleteById(idClient);
        return new ApiResponseDto<>(HttpStatus.OK.value(),"El cliente se elimino de forma correcta",null);
    }

    private boolean validateInput(String inputField){
        return inputField == null || inputField.trim().isEmpty();
    }
}
