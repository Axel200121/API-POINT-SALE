package com.ams.dev.sale.point.Services;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.SaleDetailDto;

import java.util.List;
import java.util.Set;

public interface SaleDetailService {

    ApiResponseDto createSaleDetail(Set<SaleDetailDto> listSaleDetailDto);

    ApiResponseDto updateSaleDetail(String id, List<SaleDetailDto>saleDto);

    ApiResponseDto getSaleDetail(String id);

    ApiResponseDto getAllSaleDetails();

    ApiResponseDto deleteSaleDetail(String id);

}
