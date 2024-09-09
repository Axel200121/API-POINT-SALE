package com.ams.dev.sale.point.Mappers;

import com.ams.dev.sale.point.Dtos.*;
import com.ams.dev.sale.point.Entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaleDetailMapper {

    @Autowired
    private ProductMapper productMapper;

    //Covierte un User a UserDto
    public SaleDetailDto toDto(SaleDetail saleDetail) {
        if (saleDetail == null)
            return null;

        SaleDetailDto saleDetailDto = new SaleDetailDto();
        saleDetailDto.setId(saleDetail.getId());
        saleDetailDto.setQuantity(saleDetail.getQuantity());
        saleDetailDto.setUnitPrice(saleDetail.getUnitPrice());
        ProductDto productDto = productMapper.toDto(saleDetail.getProduct());
        saleDetailDto.setProduct(productDto);
        saleDetailDto.setCreatedAt(saleDetail.getCreatedAt());
        saleDetailDto.setUpdatedAt(saleDetail.getUpdatedAt());
        return saleDetailDto;
    }

    //Covierte un UserDto a Entity
    public SaleDetail toEntity(SaleDetailDto saleDetailDto) {
        if (saleDetailDto == null)
            return null;

        SaleDetail saleDetail = new SaleDetail();
        saleDetail.setId(saleDetailDto.getId());
        saleDetail.setQuantity(saleDetailDto.getQuantity());
        saleDetail.setUnitPrice(saleDetailDto.getUnitPrice());
        Product product = productMapper.toEntity(saleDetailDto.getProduct());
        saleDetail.setProduct(product);
        saleDetail.setCreatedAt(saleDetailDto.getCreatedAt());
        saleDetail.setUpdatedAt(saleDetailDto.getUpdatedAt());

        return saleDetail;
    }
}
