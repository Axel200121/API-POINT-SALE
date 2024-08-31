package com.ams.dev.sale.point.Services.impl;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.CategoryDto;
import com.ams.dev.sale.point.Entities.Category;
import com.ams.dev.sale.point.Mappers.CategoryMapper;
import com.ams.dev.sale.point.Repositories.CategoryRepository;
import com.ams.dev.sale.point.Services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ApiResponseDto createCategory(CategoryDto categoryDto) {
        Optional<Category> categoryBD = categoryRepository.findByName(categoryDto.getName().toLowerCase());
        if (categoryBD.isPresent())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Ya existe una categoria con ese nombre",null);

        if (validationField(categoryDto.getName()))
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Nesecitas ingresar un nombre a la categoria",null);

        Category categorySave = categoryRepository.save(categoryMapper.toEntity(categoryDto));
        return new ApiResponseDto<>(HttpStatus.CREATED.value(),"Categoria creada exitosamente",categoryMapper.toDto(categorySave));
    }

    @Override
    public ApiResponseDto updateCategory(String id, CategoryDto categoryDto) {
        Optional<Category> categoryBD = categoryRepository.findById(id);
        if (categoryBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No existe esa categria ne la BD",null);

        Category categoryEdit = categoryBD.get();
        categoryEdit.setName(categoryDto.getName());
        categoryEdit.setDescription(categoryDto.getDescription());
        Category categorySave = categoryRepository.save(categoryEdit);

        return new ApiResponseDto<>(HttpStatus.OK.value(),"Categoria actualizada correctamente",categoryMapper.toDto(categorySave));
    }

    @Override
    public ApiResponseDto desactiveCategory(String id) {
        return null;
    }

    @Override
    public ApiResponseDto deleteCategory(String id) {
        return null;
    }

    @Override
    public ApiResponseDto getCategory(String id) {
        Optional<Category> categoryBD = categoryRepository.findById(id);
        if (categoryBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No existe esa categria ne la BD",null);

        Category getCategory = categoryBD.get();
        CategoryDto categoryDto = categoryMapper.toDto(getCategory);
        return new ApiResponseDto<>(HttpStatus.OK.value(),"Categoria encontrada",categoryDto);
    }

    @Override
    public ApiResponseDto getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        if (categories.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No tienes categorias registradas",null);

        List<CategoryDto> categoryDtos = categories.stream().map(categoryMapper::toDto).collect(Collectors.toList());

        return new ApiResponseDto<>(HttpStatus.OK.value(),"Listado de categorias",categoryDtos);
    }

    private boolean validationField(String inputField){
        return inputField == null || inputField.trim().isEmpty();
    }
}
