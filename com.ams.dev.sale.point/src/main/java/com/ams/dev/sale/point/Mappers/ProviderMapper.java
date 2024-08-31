package com.ams.dev.sale.point.Mappers;

import com.ams.dev.sale.point.Dtos.ProviderDto;
import com.ams.dev.sale.point.Entities.Provider;
import org.springframework.stereotype.Component;

@Component
public class ProviderMapper {

    public ProviderDto toDto(Provider provider){
        if (provider == null) return null;

        ProviderDto providerDto = new ProviderDto();
        providerDto.setId(provider.getId());
        providerDto.setName(provider.getName());
        providerDto.setAddress(provider.getAddress());
        providerDto.setEmail(provider.getEmail());
        providerDto.setPhone(provider.getPhone());
        providerDto.setCreatedAt(provider.getCreatedAt());
        providerDto.setUpdatedAt(provider.getUpdatedAt());

        return providerDto;
    }

    public Provider toEntity(ProviderDto providerDto){
        if (providerDto == null) return null;
        Provider provider = new Provider();
        provider.setId(providerDto.getId());
        provider.setName(providerDto.getName());
        provider.setAddress(providerDto.getAddress());
        provider.setEmail(providerDto.getEmail());
        provider.setPhone(providerDto.getPhone());
        provider.setCreatedAt(providerDto.getCreatedAt());
        provider.setUpdatedAt(providerDto.getUpdatedAt());
        return provider;
    }
}
