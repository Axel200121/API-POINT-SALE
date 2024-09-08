package com.ams.dev.sale.point.Services;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.ProductDto;
import com.ams.dev.sale.point.Dtos.ProviderDto;

public interface ProductService {

    ApiResponseDto createProduct(ProductDto productDto);

    ApiResponseDto updateProduct(String id, ProductDto productDto);

    ApiResponseDto getProduct(String id);

    ApiResponseDto getAllProducts();

    ApiResponseDto deleteProduct(String id);

    ApiResponseDto desactiveProduct(String id);
}
