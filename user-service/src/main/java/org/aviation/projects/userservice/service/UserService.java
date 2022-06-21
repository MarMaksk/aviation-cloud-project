package org.aviation.projects.userservice.service;

import org.aviation.projects.userservice.dto.UserDTO;
import org.aviation.projects.userservice.entity.User;
import org.aviation.projects.userservice.entity.enums.ERole;
import org.aviation.projects.userservice.exceptions.UserExistException;
import org.aviation.projects.userservice.payload.request.SignupRequest;
import org.aviation.projects.userservice.repository.UserRepository;
import org.aviation.projects.userservice.service.mapper.UserDTOMapper;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RequiredArgsConstructor
public class UserService {
    static Logger LOG = LoggerFactory.getLogger(UserService.class);

    UserRepository userRepository;
    BCryptPasswordEncoder bCryptPasswordEncoder;
    ModelMapper mapper;
    UserDTOMapper userDTOMapper;

    public User createUser(SignupRequest userIn) {
        User user = new User();
        user.setFirstname(userIn.getFirstname());
        user.setLastname(userIn.getLastname());
        user.setUsername(userIn.getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(userIn.getPassword()));
        user.getRoles().add(ERole.ROLE_USER);
        try {
            LOG.info("Saving user {}", userIn.getUsername());
            return userRepository.save(user);
        } catch (Exception e) {
            LOG.error("Error during registration, {}", e.getMessage());
            throw new UserExistException("The user " + user.getUsername() + " already exist");
        }
    }

    public UserDTO updateUser(UserDTO userDTO, Principal principal) {
        User user = getUserByPrincipal(principal.getName());
        mapper.map(userDTO, user);
        return userDTOMapper.toDTO(
                userRepository.save(user)
        );
    }

    private User getUserByPrincipal(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found. Name " + username));
    }

    public UserDTO getCurrentUser(String username) {
        User user = getUserByPrincipal(username);
        return userDTOMapper.toDTO(user);
    }

    public UserDTO getUserById(Long id) {
        return userDTOMapper.toDTO(
                userRepository.findById(id)
                        .orElseThrow(() -> new UserExistException("User not found"))
        );
    }
}
