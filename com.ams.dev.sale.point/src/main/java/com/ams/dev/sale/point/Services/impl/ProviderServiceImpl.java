package com.ams.dev.sale.point.Services.impl;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.ProviderDto;
import com.ams.dev.sale.point.Entities.Provider;
import com.ams.dev.sale.point.Mappers.ProviderMapper;
import com.ams.dev.sale.point.Repositories.ProviderRepository;
import com.ams.dev.sale.point.Services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private ProviderMapper providerMapper;

    @Override
    public ApiResponseDto createProvider(ProviderDto providerDto) {

        if (validateInput(providerDto.getName()) || validateInput(providerDto.getPhone()) || validateInput(providerDto.getAddress()) ||
                validateInput(providerDto.getEmail()))
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Faltan campos por informar, verifica tus datos",null);

        Provider providerSave = providerRepository.save(providerMapper.toEntity(providerDto));

        return new ApiResponseDto<>(HttpStatus.CREATED.value(),"Proveedor creado exitosamente",providerMapper.toDto(providerSave));
    }

    @Override
    public ApiResponseDto updateProvider(String id, ProviderDto providerDto) {
        Optional<Provider> providerBD = providerRepository.findById(id);
        if (providerBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.OK.value(),"El proveedor no existe en la BD",null);

        Provider providerEdit = providerBD.get();
        providerEdit.setName(providerDto.getName());
        providerEdit.setPhone(providerDto.getPhone());
        providerEdit.setEmail(providerDto.getEmail());
        providerEdit.setAddress(providerDto.getAddress());
        Provider providerUpdate = providerRepository.save(providerEdit);

        return new ApiResponseDto<>(HttpStatus.OK.value(),"Proveedor actualiazado correctamente",providerMapper.toDto(providerUpdate));
    }

    @Override
    public ApiResponseDto getProvider(String id) {
        Optional<Provider> providerBD = providerRepository.findById(id);
        if (providerBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.OK.value(),"El proveedor no existe en la BD",null);
        Provider provider = providerBD.get();
        return new ApiResponseDto<>(HttpStatus.OK.value(),"Proveedor encontrado",providerMapper.toDto(provider));
    }

    @Override
    public ApiResponseDto getAllProviders() {
        List<Provider> providerList = providerRepository.findAll();
        if (providerList.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No tienes proveedores registrados",null);

        List<ProviderDto> providerDtoList = providerList.stream().map(providerMapper::toDto).collect(Collectors.toList());
        return new ApiResponseDto<>(HttpStatus.OK.value(),"Lista de proveedores",providerDtoList);
    }

    @Override
    public ApiResponseDto deleteProvider(String id) {
        return null;
    }

    @Override
    public ApiResponseDto desactiveProvider(String id) {
        return null;
    }

    private boolean validateInput(String input){
        return input==null && input.trim().isEmpty();
    }
}
