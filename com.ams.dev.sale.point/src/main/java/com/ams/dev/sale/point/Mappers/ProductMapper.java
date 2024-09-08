package com.ams.dev.sale.point.Mappers;

import com.ams.dev.sale.point.Dtos.*;
import com.ams.dev.sale.point.Entities.*;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    //Covierte un User a UserDto
    public ProductDto toDto(Product product) {
        if (product == null)
            return null;

        ProductDto productDto = new ProductDto();
        productDto.setId(product.getId());
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setPrice(product.getPrice());
        productDto.setStock(product.getStock());
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(product.getCategory().getId());
        categoryDto.setName(product.getCategory().getName());
        categoryDto.setDescription(product.getCategory().getDescription());
        categoryDto.setCreatedAt(product.getCategory().getCreatedAt());
        categoryDto.setUpdatedAt(product.getCategory().getUpdatedAt());
        ProviderDto providerDto = new ProviderDto();
        providerDto.setId(product.getProvider().getId());
        providerDto.setName(product.getProvider().getName());
        providerDto.setAddress(product.getProvider().getAddress());
        providerDto.setPhone(product.getProvider().getPhone());
        providerDto.setEmail(product.getProvider().getEmail());
        providerDto.setCreatedAt(product.getProvider().getCreatedAt());
        providerDto.setUpdatedAt(product.getProvider().getUpdatedAt());
        productDto.setCategory(categoryDto);
        productDto.setProvider(providerDto);
        productDto.setCreatedAt(product.getCreatedAt());
        productDto.setUpdatedAt(product.getUpdatedAt());
        return productDto;
    }

    //Covierte un UserDto a Entity
    public Product toEntity(ProductDto productDto) {
        if (productDto == null)
            return null;

        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setStock(productDto.getStock());
        Category category = new Category();
        category.setId(productDto.getCategory().getId());
        category.setName(productDto.getCategory().getName());
        category.setDescription(productDto.getCategory().getDescription());
        category.setCreatedAt(productDto.getCategory().getCreatedAt());
        category.setUpdatedAt(productDto.getCategory().getUpdatedAt());
        Provider provider = new Provider();
        provider.setId(productDto.getProvider().getId());
        provider.setName(productDto.getProvider().getName());
        provider.setAddress(productDto.getProvider().getAddress());
        provider.setPhone(productDto.getProvider().getPhone());
        provider.setEmail(productDto.getProvider().getEmail());
        provider.setCreatedAt(productDto.getProvider().getCreatedAt());
        provider.setUpdatedAt(productDto.getProvider().getUpdatedAt());
        product.setCategory(category);
        product.setProvider(provider);
        product.setCreatedAt(productDto.getCreatedAt());
        product.setUpdatedAt(productDto.getUpdatedAt());
        return product;
    }
}
