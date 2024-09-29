package com.ams.dev.sale.point.Services.impl;

import com.ams.dev.sale.point.Dtos.ApiResponseDto;
import com.ams.dev.sale.point.Dtos.TokenDto;
import com.ams.dev.sale.point.Dtos.UserDto;
import com.ams.dev.sale.point.Entities.Role;
import com.ams.dev.sale.point.Entities.User;
import com.ams.dev.sale.point.Mappers.RoleMapper;
import com.ams.dev.sale.point.Mappers.UserMapper;
import com.ams.dev.sale.point.Repositories.RoleRepository;
import com.ams.dev.sale.point.Repositories.UserRepository;
import com.ams.dev.sale.point.Services.UserService;
import com.ams.dev.sale.point.security.jwt.JwtService;
import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;
    @Autowired
    private AuthenticationManager authenticationManager;


    @Override
    public ApiResponseDto createUser(UserDto userDto) {
        Optional<User> userBD = userRepository.findByEmail(userDto.getEmail().toLowerCase());
        if (userBD.isPresent())
            return new ApiResponseDto<>(HttpStatus.CONFLICT.value(),"Este correo ya existe ingresa otro correo por favor",null);

        if (validationField(userDto.getName()) || validationField(userDto.getLastName()) || validationField(userDto.getPhone()) ||
            validationField(userDto.getEmail()) || validationField(userDto.getPassword()) || validationField(userDto.getAddress()))
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Faltan campos por informar, verifica tus datos",null);

        if (!userDto.getPassword().equals(userDto.getPasswordConfirm()))
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Las contraseñas no coinciden. Por favor, verifica que ambas contraseñas sean iguales.",null);

        Optional<Role> assignRole = roleRepository.findById(userDto.getRole().getId());
        Role role = assignRole.get();
        User user = new User();

        user.setName(userDto.getName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setAddress(userDto.getAddress());
        user.setRole(role);
        user.setUpdatedAt(new Date());

        User userSave = userRepository.save(user);
        return new ApiResponseDto<>(HttpStatus.CREATED.value(),"Usuario registrado exitosamente",userMapper.toDto(userSave));
    }

    @Override
    public ApiResponseDto updateUser(String idUser, UserDto userDto) {

        Optional<User> userBD = userRepository.findById(idUser);
        if (userBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"El usuario no existe en la base de datos, verifica su identificador unico",null);

        Optional<Role> roleBD = roleRepository.findById(userDto.getRole().getId());
        if (roleBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"El Rol no existe en la base de datos, verifica su identificador unico",null);

        if (!userDto.getPassword().equals(userDto.getPasswordConfirm()))
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"Las contraseñas no coinciden. Por favor, verifica que ambas contraseñas sean iguales.",null);

        User editUser = userBD.get();
        editUser.setName(userDto.getName());
        editUser.setLastName(userDto.getLastName());
        editUser.setPhone(userDto.getPhone());
        editUser.setEmail(userDto.getEmail());
        editUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        editUser.setAddress(userDto.getAddress());
        editUser.setRole(roleBD.get());
        editUser.setUpdatedAt(new Date());
        User updateUser = userRepository.save(editUser);

        return new ApiResponseDto<>(HttpStatus.OK.value(),"Usuario actualizado correctamente", userMapper.toDto(updateUser));

    }

    @Override
    public ApiResponseDto desactiveUser(String idUser) {
        return null;
    }

    @Override
    public ApiResponseDto deleteUser(String idUser) {
        Optional<User> userBD = userRepository.findById(idUser);
        if (userBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"El usuario ya fue eliminado",null);

        userRepository.deleteById(idUser);
        return new ApiResponseDto<>(HttpStatus.OK.value(),"El usuario se ha eliminado correctamente",null);
    }

    @Override
    public ApiResponseDto getUser(String idUser) {
        Optional<User> userBD = userRepository.findById(idUser);
        if (userBD.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(),"No se encontro el usuario en la base de datos",null);

        return new ApiResponseDto<>(HttpStatus.OK.value(),"Usuario encontrado",userMapper.toDto(userBD.get()));
    }

    @Override
    public ApiResponseDto getAllUser(String roleId) {
        List<User> userList;

        if (roleId != null && !roleId.isEmpty()) {
            userList = userRepository.findByRole_Id(roleId);
        } else {
            userList = userRepository.findAll();
        }

        if (userList.isEmpty())
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), "No hay registros en la base de datos", null);

        List<UserDto> userDtoList = userList.stream().map(userMapper::toDto).collect(Collectors.toList());

        return new ApiResponseDto<>(HttpStatus.OK.value(), "Listado de usuarios del sistema", userDtoList);
    }

    @Override
    public ApiResponseDto login(UserDto userDto) {
        if (validationField(userDto.getEmail()) || validationField(userDto.getPassword()))
            return new ApiResponseDto<>(HttpStatus.BAD_REQUEST.value(), "El usuario o contraseña no vienen informados", null);

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        Optional<User> userBD = userRepository.findByEmail(userDto.getEmail());
        UserDto user = userMapper.toDto(userBD.get());

        TokenDto responseToken = new TokenDto();
        responseToken.setIdUser(user.getId());
        responseToken.setName(user.getName());
        responseToken.setLastName(user.getLastName());
        responseToken.setIdRole(user.getRole().getId());
        responseToken.setRole(user.getRole().getName());
        responseToken.setToken(jwtService.generateToken(userBD.get()));
        responseToken.setExpiresIn(jwtService.getExpirationTime());

        return new ApiResponseDto<>(HttpStatus.OK.value(),"Bienvenido al sistema",responseToken);
    }

    private boolean validationField(String inputField){
        return inputField == null || inputField.trim().isEmpty();
    }
}
