package com.ams.dev.sale.point.Services;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.ProviderDto;

public interface ProviderService {
    ApiResponseDto createProvider(ProviderDto providerDto);

    ApiResponseDto updateProvider(String id, ProviderDto providerDto);

    ApiResponseDto getProvider(String id);

    ApiResponseDto getAllProviders();

    ApiResponseDto deleteProvider(String id);

    ApiResponseDto desactiveProvider(String id);
}
