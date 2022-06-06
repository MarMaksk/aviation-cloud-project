package com.proj.demo;

import org.apache.kafka.clients.producer.Partitioner;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.aviation.config.MapperConfig;
import org.aviation.entity.AbstractEntity;
import org.aviation.entity.InfoForOrder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.KafkaListener;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.SQLException;

@SpringBootApplication
@EnableDiscoveryClient
@RefreshScope
//@ConfigurationPropertiesScan("org.aviation.config")
@Import({MapperConfig.class})
public class FlightCateringApplication {

//    @KafkaListener(topics = "catering")
    public void test(InfoForOrder info) throws SQLException {
        System.out.println("OVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVEROVER");
        System.out.println(info);
    }

    public static void main(String[] args) {
        SpringApplication.run(FlightCateringApplication.class, args);
    }

//    @KafkaListener(id = "myId", topics = "catering")
//    public void listen(String in, RecordMetadata rm, Partitioner partitioner) {
//        System.out.println(in);
//        System.out.println(rm.topic());
//        System.out.println(partitioner);
//
//    }

}
