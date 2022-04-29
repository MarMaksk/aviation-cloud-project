package com.proj.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.event.EventListener;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
public class FlightCateringApplication {

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void test() throws SQLException {
        System.out.println("OVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVER");
    }

    public static void main(String[] args) {
        SpringApplication.run(FlightCateringApplication.class, args);
    }

    @EventListener({EnvironmentChangeEvent.class})
    public void onRefresh() {
        System.out.println("environment Changed..");
    }


}
