package org.aviation.projects.userservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.userservice.dto.UserDTO;
import org.aviation.projects.userservice.service.UserService;
import org.aviation.projects.userservice.validations.ResponseErrorValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("avia/user")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    static Logger LOG = LoggerFactory.getLogger(UserController.class);
    UserService userService;
    ResponseErrorValidation responseErrorValidation;

    @GetMapping("/")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        LOG.info("getCurrentUser method called in UserController");
        UserDTO currentUser = userService.getCurrentUser(principal.getName());
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long userId) {
        LOG.info("getUserProfile method called in UserController");
        UserDTO currentUser = userService.getUserById(userId);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    @PostMapping("/update")
    public ResponseEntity<Object> updateUser(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult, Principal principal) {
        LOG.info("updateUser method called in UserController");
        ResponseEntity<Object> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        UserDTO res = userService.updateUser(userDTO, principal);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }
}
