package com.ams.dev.sale.point.Controllers;


import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.UserDto;
import com.ams.dev.sale.point.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
@CrossOrigin
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponseDto> createUser(@RequestBody UserDto userDto){
        ApiResponseDto response = userService.createUser(userDto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponseDto> createUser(@PathVariable String id, @RequestBody UserDto userDto){
        ApiResponseDto response = userService.updateUser(id,userDto);
        return new ResponseEntity<>(response, HttpStatus.valueOf(response.getStatusCode()));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponseDto> deleteUser(@PathVariable String id){
        ApiResponseDto response = userService.deleteUser(id);
        return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ApiResponseDto> getUser(@PathVariable String id){
        ApiResponseDto response = userService.getUser(id);
        return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
    }

    @GetMapping("/get-all")
    public ResponseEntity<ApiResponseDto> getAllUser(){
        ApiResponseDto response = userService.getAllUser();
        return new ResponseEntity<>(response,HttpStatus.valueOf(response.getStatusCode()));
    }





}
