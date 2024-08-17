package com.ams.dev.sale.point.Services.impl;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.RoleDto;
import com.ams.dev.sale.point.Entities.Role;
import com.ams.dev.sale.point.Mappers.RoleMapper;
import com.ams.dev.sale.point.Repositories.RoleRepository;
import com.ams.dev.sale.point.Services.RoleService;
import com.ams.dev.sale.point.Utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private RoleMapper roleMapper;


    @Override
    public ApiResponseDto createRole(RoleDto roleDto) {
        Optional<Role> existingRole = roleRepository.findByName(roleDto.getName().toLowerCase());
        if (existingRole.isPresent())
            return new ApiResponseDto(HttpStatus.CONFLICT.value(), Constants.EXISTING_ROLE.getValue(), null);

        if (validateInput(roleDto.getName()) || validateInput(roleDto.getDescription()))
            return new ApiResponseDto(HttpStatus.BAD_REQUEST.value(), Constants.EMPTY_FIELDS.getValue(), null);

        Role roleSave = roleRepository.save(roleMapper.toEntity(roleDto));

        return new ApiResponseDto(HttpStatus.CREATED.value(), Constants.ROLE_CREATING.getValue(), roleMapper.toDto(roleSave));
    }

    @Override
    public ApiResponseDto updateRole(String idRole, RoleDto roleDto) {
        Optional<Role> existingRole = roleRepository.findById(idRole);
        if (existingRole.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),Constants.NO_EXISTING_ROLE.getValue(), null);

        Role editRole = existingRole.get();
        editRole.setName(roleDto.getName());
        editRole.setDescription(roleDto.getDescription());
        editRole.setUpdatedAt(new Date());

        Role roleUpdate = roleRepository.save(editRole);

        return new ApiResponseDto(HttpStatus.CREATED.value(), Constants.UPDATE_ROLE.getValue(), roleMapper.toDto(roleUpdate));
    }

    @Override
    public ApiResponseDto desactiveRole(String idRole) {
        return null;
    }

    @Override
    public ApiResponseDto deleteRole(String idRole) {
        Optional<Role> roleBD = roleRepository.findById(idRole);
        if (roleBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),Constants.NO_EXISTING_ROLE.getValue(), null);

        roleRepository.deleteById(idRole);
        return new ApiResponseDto(HttpStatus.OK.value(),Constants.DELETE_ROLE.getValue(),new Role());
    }

    @Override
    public ApiResponseDto getRole(String idRole) {
        Optional<Role> roleBD = roleRepository.findById(idRole);
        if (roleBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),Constants.NO_EXISTING_ROLE.getValue(), null);

        Role role = roleBD.get();
        return new ApiResponseDto(HttpStatus.OK.value(),Constants.GET_ROLE.getValue(),roleMapper.toDto(role));
    }

    @Override
    public ApiResponseDto getAllRole() {
        List<Role> roleList = roleRepository.findAll();
        if (roleList.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),Constants.NO_LIST_EXISTING_ROLE.getValue(), null);

        List<RoleDto> roleDtoList = roleList.stream().map(roleMapper::toDto).collect(Collectors.toList());

        return new ApiResponseDto(HttpStatus.OK.value(),Constants.EXISTING_LIST_ROLE.getValue(),roleDtoList);
    }


    private boolean validateInput(String inputField){
        return inputField == null || inputField.trim().isEmpty();
    }
}
