package com.ams.dev.sale.point.Mappers;

import com.ams.dev.sale.point.Dtos.RoleDto;
import com.ams.dev.sale.point.Entities.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    //Covierte un Rol a RoleDto
    public RoleDto toDto(Role role) {
        if (role == null)
            return null;

        RoleDto roleDto = new RoleDto();
        roleDto.setId(role.getId());
        roleDto.setName(role.getName());
        roleDto.setDescription(role.getDescription());
        roleDto.setCreatedAt(role.getCreatedAt());
        roleDto.setUpdatedAt(role.getUpdatedAt());
        return roleDto;
    }

    //Covierte un RolDto a Entity
    public Role toEntity(RoleDto roleDto) {
        if (roleDto == null)
            return null;

        Role role = new Role();

        role.setName(roleDto.getName());
        role.setDescription(roleDto.getDescription());
        role.setCreatedAt(roleDto.getCreatedAt());
        role.setUpdatedAt(roleDto.getUpdatedAt());
        return role;
    }


}
