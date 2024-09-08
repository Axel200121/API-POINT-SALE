package com.ams.dev.sale.point.Services.impl;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.ProductDto;
import com.ams.dev.sale.point.Entities.Category;
import com.ams.dev.sale.point.Entities.Product;
import com.ams.dev.sale.point.Entities.Provider;
import com.ams.dev.sale.point.Mappers.ProductMapper;
import com.ams.dev.sale.point.Repositories.CategoryRepository;
import com.ams.dev.sale.point.Repositories.ProductRepository;
import com.ams.dev.sale.point.Repositories.ProviderRepository;
import com.ams.dev.sale.point.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper productMapper;

    @Autowired
    private ProviderRepository providerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ApiResponseDto createProduct(ProductDto productDto) {

        if (validateInput(productDto.getName()) || validateInput(productDto.getDescription()) || validateInput(String.valueOf(productDto.getPrice()))
                || validateInput(String.valueOf(productDto.getStock())))
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Faltan campos por informar, verifica tus datos",null);

        Optional<Category> categoryBD = categoryRepository.findById(productDto.getCategory().getId());
        if (categoryBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Categoria que se relaciona con el producto no existe",null);

        Optional<Provider> providerBD = providerRepository.findById(productDto.getProvider().getId());
        if (providerBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Proveedor que se relaciona con el producto no existe",null);

        Product product = productMapper.toEntity(productDto);
        product.setCategory(categoryBD.get());
        product.setProvider(providerBD.get());
        Product productSave = productRepository.save(product);
        return new ApiResponseDto<>(HttpStatus.CREATED.value(),"Producto registrado exitosamente",productMapper.toDto(productSave));
    }

    @Override
    public ApiResponseDto updateProduct(String id, ProductDto productDto) {
        Optional<Product> productBD = productRepository.findById(id);
        if (productBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No se encontro este producto",null);

        if (validateInput(productDto.getName()) || validateInput(productDto.getDescription()) || validateInput(String.valueOf(productDto.getPrice()))
                || validateInput(String.valueOf(productDto.getStock())))
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Faltan campos por informar, verifica tus datos",null);

        Optional<Category> categoryBD = categoryRepository.findById(productDto.getCategory().getId());
        Optional<Provider> providerBD = providerRepository.findById(productDto.getProvider().getId());
        Product productEdit = productBD.get();
        productEdit.setName(productDto.getName());
        productEdit.setDescription(productDto.getDescription());
        productEdit.setPrice(productDto.getPrice());
        productEdit.setStock(productDto.getStock());
        productEdit.setCategory(categoryBD.get());
        productEdit.setProvider(providerBD.get());

        Product updateProduct = productRepository.save(productEdit);
        return new ApiResponseDto<>(HttpStatus.OK.value(), "Producto actualizado correctamente", productMapper.toDto(updateProduct));
    }

    @Override
    public ApiResponseDto getProduct(String id) {
        Optional<Product> productBD = productRepository.findById(id);
        if (productBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No se encontro este producto",null);

        ProductDto productDto = productMapper.toDto(productBD.get());
        return new ApiResponseDto<>(HttpStatus.OK.value(),"Información del producto",productDto);
    }

    @Override
    public ApiResponseDto getAllProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No hay productos registrados",null);

        List<ProductDto> productDtoList = products.stream().map(productMapper::toDto).collect(Collectors.toList());
        return new ApiResponseDto<>(HttpStatus.OK.value(),"Listado de productos", productDtoList);
    }

    @Override
    public ApiResponseDto deleteProduct(String id) {
        return null;
    }

    @Override
    public ApiResponseDto desactiveProduct(String id) {
        return null;
    }

    private boolean validateInput(String inputField){
        return inputField == null || inputField.trim().isEmpty();
    }

}
