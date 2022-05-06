package com.proj.demo;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.kafka.annotation.KafkaListener;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@EnableDiscoveryClient
public class FlightCateringApplication {

    @PostConstruct
    public void test() throws SQLException {
        System.out.println("OVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVER");

    }

    public static void main(String[] args) {
        SpringApplication.run(FlightCateringApplication.class, args);
    }

    @KafkaListener(id = "myId", topics = "topic1")
    public void listen(String in, RecordMetadata rm, Partitioner partitioner) {
        System.out.println(in);
        System.out.println(rm.topic());
        System.out.println(partitioner);

    }

}
