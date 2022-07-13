package org.aviation.projects.userservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.userservice.entity.User;
import org.aviation.projects.userservice.entity.enums.ERole;
import org.aviation.projects.userservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EmailService {

    static Logger LOG = LoggerFactory.getLogger(EmailService.class);
    UserRepository userRepository;

    public List<String> getEmails(ERole role) {
        LOG.info("getEmails method called in EmailService");
        List<User> users = userRepository.findAllByRolesContainsAndDeletedFalseAndEmailIsNotNull(role);
        return users.stream().map(User::getEmail).collect(Collectors.toList());
    }

}
