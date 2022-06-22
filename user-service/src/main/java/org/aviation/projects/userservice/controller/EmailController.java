package org.aviation.projects.userservice.controller;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.userservice.entity.enums.ERole;
import org.aviation.projects.userservice.service.EmailService;
import org.aviation.projects.userservice.service.UserService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmailController {

    EmailService emailService;

    @GetMapping
    public List<String> getCatererEmailsEmails() {
        return emailService.getEmails(ERole.ROLE_CATERER);
    }

}
