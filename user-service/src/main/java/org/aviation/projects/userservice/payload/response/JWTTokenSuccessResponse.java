package org.aviation.projects.userservice.payload.response;

import org.aviation.projects.userservice.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Set;

@Data
@AllArgsConstructor
public class JWTTokenSuccessResponse {

    private boolean success;
    private String token;
    private Set<ERole> roles;

}
