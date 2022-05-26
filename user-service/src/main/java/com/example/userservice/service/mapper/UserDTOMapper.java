package com.example.userservice.service.mapper;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;

@AllArgsConstructor
public class UserDTOMapper {

    private ModelMapper mapper;

    public User toEntity(UserDTO dto) {
        return mapper.map(dto, User.class);
    }

    public UserDTO toDTO(User entity) {
        return mapper.map(entity, UserDTO.class);
    }
}
