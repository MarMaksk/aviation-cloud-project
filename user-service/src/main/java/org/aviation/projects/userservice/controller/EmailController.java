package org.aviation.projects.userservice.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.userservice.entity.enums.ERole;
import org.aviation.projects.userservice.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("email")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class EmailController {

    static Logger LOG = LoggerFactory.getLogger(EmailController.class);
    EmailService emailService;

    @GetMapping
    public List<String> getCatererEmailsEmails() {
        LOG.info("getCatererEmailsEmails method called in EmailController");
        return emailService.getEmails(ERole.ROLE_CATERER);
    }

}
