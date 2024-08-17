package com.ams.dev.sale.point.Services;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.RoleDto;
import com.ams.dev.sale.point.Entities.Role;

public interface RoleService {

    ApiResponseDto createRole(RoleDto roleDto);

    ApiResponseDto updateRole(String idRole, RoleDto roleDto);

    ApiResponseDto desactiveRole(String idRole);

    ApiResponseDto deleteRole(String idRole);

    ApiResponseDto getRole(String idRole);

    ApiResponseDto getAllRole();

}
