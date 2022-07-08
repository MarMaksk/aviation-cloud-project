package org.aviation.projects.flightcatering;

import org.aviation.projects.commons.config.MapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
@EnableFeignClients(basePackages = "org.aviation.projects.flightcatering.feign")
@Import({MapperConfig.class})
@EnableSwagger2
public class FlightCateringApplication {

    public static void main(String[] args) {
        SpringApplication.run(FlightCateringApplication.class, args);
    }



}
