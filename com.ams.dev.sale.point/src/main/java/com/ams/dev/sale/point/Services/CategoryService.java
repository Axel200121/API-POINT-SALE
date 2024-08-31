package com.ams.dev.sale.point.Services;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.CategoryDto;
import com.ams.dev.sale.point.Dtos.RoleDto;

public interface CategoryService {
    ApiResponseDto createCategory(CategoryDto categoryDto);

    ApiResponseDto updateCategory(String id, CategoryDto categoryDto);

    ApiResponseDto desactiveCategory(String id);

    ApiResponseDto deleteCategory(String id);

    ApiResponseDto getCategory(String id);

    ApiResponseDto getAllCategories();
}
