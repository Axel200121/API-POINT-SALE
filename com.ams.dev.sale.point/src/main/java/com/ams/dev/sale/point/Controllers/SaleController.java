package com.ams.dev.sale.point.Controllers;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.SaleDto;
import com.ams.dev.sale.point.Services.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sale")
public class SaleController {

    @Autowired
    @Lazy
    private SaleService saleService;


    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseDto> getAllSales(){
        ApiResponseDto response = saleService.getAllSales();
        System.out.println("response = " + response);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponseDto> getSale(@PathVariable String id){
        ApiResponseDto response = saleService.getSale(id);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @PostMapping("/save")
    public ResponseEntity<ApiResponseDto> createSale(@RequestBody SaleDto saleDto){
        ApiResponseDto response = saleService.createSale(saleDto);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto> updateSale(@PathVariable String id, @RequestBody SaleDto saleDto){
        ApiResponseDto response = saleService.updateSale(id, saleDto);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto> deleteSale(@PathVariable String id){
        return null;
    }

}
