package com.ams.dev.sale.point.Controllers;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.UserDto;
import com.ams.dev.sale.point.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
@CrossOrigin
public class AuthenticationController {


    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto> authenticate(@RequestBody UserDto userDto) {
        ApiResponseDto response = userService.login(userDto);
        return new ResponseEntity<>(response, HttpStatusCode.valueOf(response.getStatusCode()));
    }
}
