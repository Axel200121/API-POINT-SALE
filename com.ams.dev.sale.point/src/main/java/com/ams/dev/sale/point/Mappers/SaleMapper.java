package com.ams.dev.sale.point.Mappers;

import com.ams.dev.sale.point.Dtos.*;
import com.ams.dev.sale.point.Entities.*;
import com.ams.dev.sale.point.Services.impl.SaleServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class SaleMapper {

    private static final Logger LOGGER = LogManager.getLogger(SaleServiceImpl.class);


    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private  UserMapper userMapper;

    @Autowired
    @Lazy
    private SaleDetailMapper saleDetailMapper;

    @Autowired
    private RoleMapper roleMapper;

    //Covierte un User a UserDto
    public SaleDto toDto(Sale sale) {
        if (sale == null)
            return null;

        SaleDto saleDto = new SaleDto();
        saleDto.setId(sale.getId());
        saleDto.setSaleDate(sale.getSaleDate());
        saleDto.setTotal(sale.getTotal());
        ClientDto clientDto = clientMapper.toDto(sale.getClient());
        UserDto userDto = userMapper.toDto(sale.getUser());
        Set<SaleDetailDto> saleDetailDtos = sale.getSaleDetail().stream()
                .map(saleDetailMapper::toDto) // Usar el mapper para convertir cada SaleDetail a SaleDetailDto
                .collect(Collectors.toSet());
        saleDto.setSaleDetail(saleDetailDtos);
        saleDto.setClient(clientDto);
        saleDto.setUser(userDto);
        saleDto.setCreatedAt(sale.getCreatedAt());
        saleDto.setUpdatedAt(sale.getUpdatedAt());
        return saleDto;
    }

    //Covierte un UserDto a Entity
    public Sale toEntity(SaleDto saleDto) {
        if (saleDto == null)
            return null;

        Sale sale = new Sale();
        sale.setId(saleDto.getId());
        sale.setSaleDate(saleDto.getSaleDate());
        sale.setTotal(saleDto.getTotal());
        Client client = clientMapper.toEntity(saleDto.getClient());
        User user = userMapper.toEntity(saleDto.getUser());
        sale.setClient(client);
        sale.setUser(user);
        sale.setCreatedAt(saleDto.getCreatedAt());
        sale.setUpdatedAt(saleDto.getUpdatedAt());

        return sale;
    }


}
