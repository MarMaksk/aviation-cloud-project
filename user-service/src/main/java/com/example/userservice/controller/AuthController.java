package com.example.userservice.controller;

import com.example.userservice.dto.UserDTO;
import com.example.userservice.entity.enums.ERole;
import com.example.userservice.payload.request.LoginRequest;
import com.example.userservice.payload.request.SignupRequest;
import com.example.userservice.payload.response.JWTTokenSuccessResponse;
import com.example.userservice.security.JWTTokenProvider;
import com.example.userservice.security.SecurityConstants;
import com.example.userservice.service.UserService;
import com.example.userservice.validations.ResponseErrorValidation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Set;

@CrossOrigin
@RestController
@RequestMapping("/avia/auth")
@PreAuthorize("permitAll()")
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class AuthController {

    ResponseErrorValidation responseErrorValidation;
    UserService userService;
    AuthenticationManager authenticationManager;
    JWTTokenProvider jwtTokenProvider;

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors))
            return errors;
        String jwt = auth(loginRequest.getUsername(), loginRequest.getPassword());
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt, loadRoles(loginRequest.getUsername())));
    }


    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@Valid @RequestBody SignupRequest signupRequest, BindingResult bindingResult, Principal principal) {
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors))
            return errors;
        userService.createUser(signupRequest);
        String jwt = auth(signupRequest.getUsername(), signupRequest.getPassword());
        return ResponseEntity.ok(new JWTTokenSuccessResponse(true, jwt, loadRoles(signupRequest.getUsername())));
    }

    private String auth(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password
        ));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);
    }

    private Set<ERole> loadRoles(String username) {
        UserDTO currentUser = userService.getCurrentUser(username);
        return currentUser.getRoles();
    }
}
