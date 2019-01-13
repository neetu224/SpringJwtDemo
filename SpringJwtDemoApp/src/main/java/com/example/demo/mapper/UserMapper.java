package com.example.demo.mapper;

import org.mapstruct.Mapper;
import com.example.demo.dto.UserDto;
import com.example.demo.model.User;

@Mapper(componentModel="spring")
public interface UserMapper {
UserDto userDomainToDto(User optional);
User UserDtoToDomain(UserDto user);
}
