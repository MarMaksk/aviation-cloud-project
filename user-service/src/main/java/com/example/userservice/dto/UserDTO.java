package com.example.userservice.dto;

import com.example.userservice.entity.enums.ERole;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Data
public class UserDTO {

    private Long id;
    @NotEmpty
    private String username;
    @NotEmpty
    private String firstname;
    @NotEmpty
    private String lastname;

    private String bio;

    private Set<ERole> roles;
}
