package org.aviation.projects.flightcatering;

import org.aviation.projects.commons.config.MapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@Import({MapperConfig.class})
public class FlightCateringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightCateringApplication.class, args);
    }

}
