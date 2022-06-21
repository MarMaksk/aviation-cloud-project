package org.aviation.projects.flightorder;

import org.aviation.projects.commons.config.MapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "org.aviation.projects.flightorder.feign")
@Import({MapperConfig.class})
public class FlightOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightOrderApplication.class, args);
    }


}
