package com.ams.dev.sale.point.Mappers;

import com.ams.dev.sale.point.Dtos.CategoryDto;
import com.ams.dev.sale.point.Entities.Category;
import org.springframework.stereotype.Component;

@Component
public class CategoryMapper {

    public CategoryDto toDto(Category category){
        if (category== null) return null;

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
        categoryDto.setCreatedAt(category.getCreatedAt());
        categoryDto.setUpdatedAt(category.getUpdatedAt());
        return categoryDto;
    }

    public Category toEntity(CategoryDto categoryDto){
        if (categoryDto==null) return null;

        Category category = new Category();
        category.setId(categoryDto.getId());
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category.setCreatedAt(categoryDto.getCreatedAt());
        category.setUpdatedAt(categoryDto.getUpdatedAt());
        return category;
    }
}
