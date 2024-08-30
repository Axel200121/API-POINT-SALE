package com.ams.dev.sale.point.Services;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.ClientDto;
import com.ams.dev.sale.point.Dtos.UserDto;

public interface ClientService {

    ApiResponseDto createClient(ClientDto clientDto);

    ApiResponseDto updateClient(String idClient, ClientDto clientDto);

    ApiResponseDto getClient(String id);

    ApiResponseDto getAllClients();

    ApiResponseDto deleteClient(String idClient);

}
