package org.aviation.projects.userservice.dto;

import lombok.Data;
import org.aviation.projects.userservice.entity.enums.ERole;

import javax.validation.constraints.NotEmpty;
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

    private String email;

    private String bio;

    private Set<ERole> roles;
}
