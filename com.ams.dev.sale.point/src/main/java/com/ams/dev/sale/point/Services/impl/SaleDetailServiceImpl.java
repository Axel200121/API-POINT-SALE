package com.ams.dev.sale.point.Services.impl;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.SaleDetailDto;
import com.ams.dev.sale.point.Entities.Product;
import com.ams.dev.sale.point.Entities.SaleDetail;
import com.ams.dev.sale.point.Mappers.SaleDetailMapper;
import com.ams.dev.sale.point.Repositories.ProductRepository;
import com.ams.dev.sale.point.Repositories.SaleDetailRepository;
import com.ams.dev.sale.point.Services.SaleDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SaleDetailServiceImpl implements SaleDetailService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SaleDetailRepository saleDetailRepository;

    @Autowired
    private SaleDetailMapper saleDetailMapper;

    @Override
    public ApiResponseDto createSaleDetail(Set<SaleDetailDto> listSaleDetailDto) {
        Double total = 0.0;
        for (SaleDetailDto saleDetailDto : listSaleDetailDto) {
            // Buscar el producto en la base de datos
            Optional<Product> productBD = productRepository.findById(saleDetailDto.getProduct().getId());
            if (productBD.isEmpty())
                return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), "El producto con ID " + saleDetailDto.getProduct().getId() + " no existe en la base de datos", null);

            Product product = productBD.get();
            // Verificar si hay suficiente stock
            if (product.getStock() < saleDetailDto.getQuantity())
                return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), "Stock insuficiente para el producto con ID: " + product.getId(), null);

            // Restar la cantidad del stock
            product.setStock(product.getStock() - saleDetailDto.getQuantity());
            productRepository.save(product);
            // Calcular el total para cada detalle de venta
            total += saleDetailDto.getQuantity() * saleDetailDto.getUnitPrice();
            // Crear y guardar el detalle de venta
            SaleDetail saleDetail = saleDetailMapper.toEntity(saleDetailDto);
            saleDetailRepository.save(saleDetail);
        }

        return new ApiResponseDto<>(HttpStatus.CREATED.value(), "Se registró el detalle de la venta", total);
    }



    @Override
    public ApiResponseDto updateSaleDetail(String id, List<SaleDetailDto> saleDto) {
        return null;
    }

    @Override
    public ApiResponseDto getSaleDetail(String id) {
        return null;
    }

    @Override
    public ApiResponseDto getAllSaleDetails() {
        return null;
    }

    @Override
    public ApiResponseDto deleteSaleDetail(String id) {
        return null;
    }
}
