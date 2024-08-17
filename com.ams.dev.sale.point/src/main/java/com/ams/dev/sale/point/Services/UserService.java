package com.ams.dev.sale.point.Services;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.RoleDto;
import com.ams.dev.sale.point.Dtos.UserDto;

public interface UserService {

    ApiResponseDto createUser(UserDto userDto);

    ApiResponseDto updateUser(String idUser, UserDto userDto);

    ApiResponseDto desactiveUser(String idUser);

    ApiResponseDto deleteUser(String idUser);

    ApiResponseDto getUser(String idUser);

    ApiResponseDto getAllUser();

    ApiResponseDto login(UserDto userDto);


}
