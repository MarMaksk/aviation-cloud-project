package com.example.userservice;

import com.example.userservice.entity.User;
import com.example.userservice.entity.enums.ERole;
import com.example.userservice.repository.UserRepository;
import org.aviation.config.MapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.HashSet;

@SpringBootApplication
@Import(MapperConfig.class)
public class UserServiceApplication {


    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;


    @PostConstruct
    public void test() {
        String admin = bCryptPasswordEncoder.encode("admin");
        String users = bCryptPasswordEncoder.encode("user");
        System.out.println(admin);
        System.out.println(users);
        User user = new User();
        user.setPassword("admin");
        user.setLastname("admin");
        user.setFirstname("admin");
        user.setUsername("admin");
        HashSet<ERole> objects = new HashSet<>();
        objects.add(ERole.ROLE_USER);
        objects.add(ERole.ROLE_ADMIN);
        user.setRoles(objects);
//        userRepository.save(user);
    }

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
