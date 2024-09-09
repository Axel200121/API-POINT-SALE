package com.ams.dev.sale.point.Services;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.SaleDto;

public interface SaleService {

    ApiResponseDto createSale(SaleDto saleDto);

    ApiResponseDto updateSale(String id, SaleDto saleDto);

    ApiResponseDto getSale(String id);

    ApiResponseDto getAllSales();

    ApiResponseDto deleteSale(String id);

}
