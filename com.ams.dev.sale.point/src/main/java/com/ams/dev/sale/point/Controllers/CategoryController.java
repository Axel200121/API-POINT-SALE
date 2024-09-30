package com.ams.dev.sale.point.Controllers;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.CategoryDto;
import com.ams.dev.sale.point.Dtos.ProviderDto;
import com.ams.dev.sale.point.Services.CategoryService;
import jakarta.persistence.Access;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/category")
@RestController
@CrossOrigin
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/create")
    ResponseEntity<ApiResponseDto> createCategory(@RequestBody CategoryDto categoryDto){
        ApiResponseDto response =  categoryService.createCategory(categoryDto);
        return  new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<ApiResponseDto> updateCategory(@PathVariable String id, @RequestBody CategoryDto categoryDto){
        ApiResponseDto response = categoryService.updateCategory(id, categoryDto);
        return  new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @GetMapping("/get/{id}")
    ResponseEntity<ApiResponseDto> getCategory(@PathVariable String id){
        ApiResponseDto response = categoryService.getCategory(id);
        return  new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @GetMapping("/get-all")
    ResponseEntity<ApiResponseDto> getAllCategories(){
        ApiResponseDto response = categoryService.getAllCategories();
        return  new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<ApiResponseDto> deleteCategory(@PathVariable String id){
        ApiResponseDto response = categoryService.deleteCategory(id);
        return  new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }
}
