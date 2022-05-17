package com.proj.flight;

import com.proj.flight.dto.FlightDTO;
import com.proj.flight.service.FlightService;
import lombok.AllArgsConstructor;
import org.aviation.config.MapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableDiscoveryClient
@AllArgsConstructor
@EnableFeignClients(basePackages = "com.proj.flight.feign")
@Import(MapperConfig.class)
public class FlightApplication {

    KafkaTemplate<String, String> template;
    FlightService flightService;


    @PostConstruct
    public void test() throws ExecutionException, InterruptedException {
        template.send("topic1", "test");
        FlightDTO dto = new FlightDTO();
        dto.setIataCode("CHECK");
        dto.setDeparture(LocalDateTime.now());
        flightService.create(dto);

        System.out.println("FTUUDSAJASKDKKASKDAS");
        ListenableFuture<SendResult<String, String>> send = template.send("topic1", "SLLSDSLD");
        System.out.println(send.get().toString());
        System.out.println(send.get().getProducerRecord().toString());
        System.out.println(send.get().getRecordMetadata().toString());
    }

    public static void main(String[] args) {
        SpringApplication.run(FlightApplication.class, args);
    }

}
