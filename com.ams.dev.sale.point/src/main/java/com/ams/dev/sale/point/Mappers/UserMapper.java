package com.ams.dev.sale.point.Mappers;

import com.ams.dev.sale.point.Dtos.RoleDto;
import com.ams.dev.sale.point.Dtos.UserDto;
import com.ams.dev.sale.point.Entities.Role;
import com.ams.dev.sale.point.Entities.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {


    //Covierte un User a UserDto
    public UserDto toDto(User user) {
        if (user == null)
            return null;

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setPhone(user.getPhone());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAddress(user.getAddress());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setUpdatedAt(user.getUpdatedAt());
        RoleDto roleDto = new RoleDto();
        roleDto.setId(user.getRole().getId());
        roleDto.setName(user.getRole().getName());
        roleDto.setDescription(user.getRole().getDescription());
        roleDto.setCreatedAt(user.getRole().getCreatedAt());
        roleDto.setUpdatedAt(user.getRole().getUpdatedAt());
        userDto.setRole(roleDto);
        return userDto;
    }

    //Covierte un UserDto a Entity
    public User toEntity(UserDto userDto, Role role) {
        if (userDto == null)
            return null;

        User user = new User();
        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAddress(userDto.getAddress());
        user.setRole(role);
        System.out.println("user en el mapper = " + user);
        return user;
    }
}
