package com.proj.demo;

import com.proj.demo.service.MailSenderService;
import org.aviation.config.MapperConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Import;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@Import({MapperConfig.class})
public class FlightCateringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightCateringApplication.class, args);
    }

}
