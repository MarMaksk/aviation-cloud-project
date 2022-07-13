package org.aviation.projects.userservice.payload.request;

import lombok.Data;
import org.aviation.projects.userservice.annotation.PasswordMatches;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@PasswordMatches
public class SignupRequest {

    @NotBlank(message = "Please enter your firstname")
    private String firstname;
    @NotBlank(message = "Please enter your lastname")
    private String lastname;
    @NotBlank(message = "Please enter your username")
    private String username;
    @NotBlank(message = "Please enter your password")
    @Size(min = 6)
    private String password;
    @NotBlank(message = "Please confirm your password")
    private String confirmPassword;

}
