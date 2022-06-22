package org.aviation.projects.userservice.service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.aviation.projects.userservice.entity.User;
import org.aviation.projects.userservice.entity.enums.ERole;
import org.aviation.projects.userservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class EmailService {

    UserRepository userRepository;

    public List<String> getEmails(ERole role) {
        List<User> users = userRepository.findAllByRolesContains(role);
        return users.stream().map(User::getEmail).collect(Collectors.toList());
    }

}
