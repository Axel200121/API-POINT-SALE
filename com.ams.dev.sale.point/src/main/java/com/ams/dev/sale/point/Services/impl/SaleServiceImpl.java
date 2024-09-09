package com.ams.dev.sale.point.Services.impl;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.SaleDetailDto;
import com.ams.dev.sale.point.Dtos.SaleDto;
import com.ams.dev.sale.point.Entities.*;
import com.ams.dev.sale.point.Mappers.ClientMapper;
import com.ams.dev.sale.point.Mappers.SaleMapper;
import com.ams.dev.sale.point.Mappers.UserMapper;
import com.ams.dev.sale.point.Repositories.*;
import com.ams.dev.sale.point.Services.SaleDetailService;
import com.ams.dev.sale.point.Services.SaleService;
import jakarta.transaction.Transactional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    private static final Logger LOGGER = LogManager.getLogger(SaleServiceImpl.class);

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    @Lazy
    private SaleDetailService saleDetailService;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private SaleDetailRepository saleDetailRepository;
    @Autowired
    private ProductRepository productRepository;

    @Override
    public ApiResponseDto createSale(SaleDto saleDto) {
        // Verificar existencia de Cliente
        Optional<Client> clientBD = clientRepository.findById(saleDto.getClient().getId());
        if (clientBD.isEmpty()) {
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), "El cliente no existe", null);
        }

        // Verificar existencia de Usuario
        Optional<User> userBD = userRepository.findById(saleDto.getUser().getId());
        if (userBD.isEmpty()) {
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), "El empleado no existe", null);
        }

        // Calcular el total
        Double total = 0.0;
        for (SaleDetailDto saleDetailDto : saleDto.getSaleDetail()) {
            total += saleDetailDto.getQuantity() * saleDetailDto.getUnitPrice();
        }

        // Crear y guardar la Venta
        Sale sale = new Sale();
        sale.setSaleDate(new Date());
        sale.setTotal(total);
        sale.setClient(clientBD.get());
        sale.setUser(userBD.get());
        Sale savedSale = saleRepository.save(sale);

        Set<SaleDetail> saleDetails = new HashSet<>();
        // Crear y guardar los detalles de la Venta
        for (SaleDetailDto saleDetailDto : saleDto.getSaleDetail()) {
            Optional<Product> productBD = productRepository.findById(saleDetailDto.getProduct().getId());
            if (productBD.isEmpty()) {
                return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), "El producto con ID " + saleDetailDto.getProduct().getId() + " no existe en la base de datos", null);
            }

            Product product = productBD.get();
            if (product.getStock() < saleDetailDto.getQuantity()) {
                return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), "Stock insuficiente para el producto con ID: " + product.getId(), null);
            }

            // Restar la cantidad del stock
            product.setStock(product.getStock() - saleDetailDto.getQuantity());
            productRepository.save(product);

            // Crear el detalle de venta
            SaleDetail saleDetail = new SaleDetail();
            saleDetail.setQuantity(saleDetailDto.getQuantity());
            saleDetail.setUnitPrice(saleDetailDto.getUnitPrice());
            saleDetail.setSale(savedSale);
            saleDetail.setProduct(product);

            saleDetailRepository.save(saleDetail);
            saleDetails.add(saleDetail);
        }

        savedSale.setSaleDetail(saleDetails);

        return new ApiResponseDto<>(HttpStatus.CREATED.value(), "Venta registrada correctamente", saleMapper.toDto(savedSale));
    }



    @Override
    public ApiResponseDto updateSale(String id, SaleDto saleDto) {
        return null;
    }

    @Transactional
    @Override
    public ApiResponseDto getSale(String id) {
        Optional<Sale> saleBD = saleRepository.findById(id);
        if (saleBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No existe esa venta en la BD",null);

        SaleDto saleDto = saleMapper.toDto(saleBD.get());
        return new ApiResponseDto<>(HttpStatus.OK.value(),"Venta encontrada",saleDto);
    }

    @Transactional
    @Override
    public ApiResponseDto getAllSales() {
        List<Sale> saleList = saleRepository.findAll();
        if (saleList.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No hay ventas registradas",null);
        List<SaleDto> saleDtoList = saleList.stream().map(saleMapper::toDto).collect(Collectors.toList());
        return new ApiResponseDto<>(HttpStatus.OK.value(),"Lista de ventas", saleDtoList);
    }

    @Override
    public ApiResponseDto deleteSale(String id) {
        return null;
    }
}
