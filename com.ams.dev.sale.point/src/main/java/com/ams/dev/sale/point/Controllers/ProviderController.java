package com.ams.dev.sale.point.Controllers;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.ProviderDto;
import com.ams.dev.sale.point.Services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/provider")
@RestController
public class ProviderController {

    @Autowired
    private ProviderService providerService;

    @PostMapping("/create")
    ResponseEntity<ApiResponseDto> createProvider(@RequestBody ProviderDto providerDto){
        ApiResponseDto response = providerService.createProvider(providerDto);
        return  new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @PutMapping("/update/{id}")
    ResponseEntity<ApiResponseDto> updateProvider(@PathVariable String id, @RequestBody ProviderDto providerDto){
        ApiResponseDto response = providerService.updateProvider(id,providerDto);
        return  new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @GetMapping("/get/{id}")
    ResponseEntity<ApiResponseDto> getProvider(@PathVariable String id){
        ApiResponseDto response = providerService.getProvider(id);
        return  new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @GetMapping("/get-all")
    ResponseEntity<ApiResponseDto> getAllProviders(){
        ApiResponseDto response = providerService.getAllProviders();
        return  new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }


}
