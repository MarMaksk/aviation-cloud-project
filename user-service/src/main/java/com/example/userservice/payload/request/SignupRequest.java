package com.example.userservice.payload.request;

import com.example.userservice.annotation.PasswordMatches;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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
