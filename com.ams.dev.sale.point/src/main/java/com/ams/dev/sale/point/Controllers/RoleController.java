package com.ams.dev.sale.point.Controllers;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.RoleDto;
import com.ams.dev.sale.point.Entities.Role;
import com.ams.dev.sale.point.Services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/roles")
@RestController
@CrossOrigin
public class RoleController {

    @Autowired
    private RoleService roleService;


    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto> createRole(@RequestBody RoleDto roleDto){
        ApiResponseDto response = roleService.createRole(roleDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto> createRole(@PathVariable String id, @RequestBody RoleDto roleDto){
        ApiResponseDto response = roleService.updateRole(id, roleDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto> deleteRole(@PathVariable String id){
        ApiResponseDto response = roleService.deleteRole(id);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @GetMapping("/get-role/{id}")
    public ResponseEntity<ApiResponseDto> getRole(@PathVariable String id){
        ApiResponseDto response = roleService.getRole(id);
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(response.getStatusCode()));
    }

    @GetMapping("/get-all-role")
    public ResponseEntity<ApiResponseDto> getAllRole(){
        ApiResponseDto response = roleService.getAllRole();
        return new ResponseEntity<>(response,HttpStatusCode.valueOf(response.getStatusCode()));
    }


}
