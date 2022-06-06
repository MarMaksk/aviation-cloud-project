package com.example.userservice.service.mapper;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.User;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserDTOMapper {

    private final ModelMapper mapper;

    public User toEntity(UserDTO dto) {
        return mapper.map(dto, User.class);
    }

    public UserDTO toDTO(User entity) {
        return mapper.map(entity, UserDTO.class);
    }
}
