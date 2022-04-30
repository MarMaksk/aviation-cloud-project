package com.proj.flight;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.core.KafkaTemplate;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableDiscoveryClient
public class FlightApplication {
    @Autowired
    KafkaTemplate<String, String> template;

    @PostConstruct
    public void test() {
        template.send("topic1", "test");
    }

    public static void main(String[] args) {
        SpringApplication.run(FlightApplication.class, args);
    }

}
