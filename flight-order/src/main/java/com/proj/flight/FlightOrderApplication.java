package com.proj.flight;

import com.proj.flight.dto.FlightDTO;
import com.proj.flight.entity.Airplane;
import com.proj.flight.entity.Airport;
import com.proj.flight.exception.NoSuchAirplaneException;
import com.proj.flight.exception.NoSuchAirportException;
import com.proj.flight.exception.NoSuchFlightException;
import com.proj.flight.repository.AirplaneRepository;
import com.proj.flight.repository.AirportRepository;
import com.proj.flight.service.FlightService;
import com.proj.flight.kafka.OrderKafkaProducer;
import lombok.AllArgsConstructor;
import org.aviation.config.MapperConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.concurrent.ExecutionException;

@SpringBootApplication
@EnableDiscoveryClient
@AllArgsConstructor
@EnableFeignClients(basePackages = "com.proj.flight.feign")
@Import({MapperConfig.class})
//@ConfigurationPropertiesScan("org.aviation.*")
public class FlightOrderApplication {

    // При обновлении постоянно требует выбрать дату снова

    FlightService flightService;
    AirplaneRepository airplaneRepository;
    AirportRepository airportRepository;
    OrderKafkaProducer sender;


    @PostConstruct
    public void justMethod() throws ExecutionException, InterruptedException, NoSuchAirplaneException, NoSuchAirportException, NoSuchFlightException {
        FlightDTO flightDTO = new FlightDTO();
        flightDTO.setDeparture(LocalDateTime.now());
        flightDTO.setFlightTime(LocalTime.of(15,0,0,0));
        flightDTO.setIcaoCodeArrival("1111");
        flightDTO.setIcaoCodeDeparture("1112");
        flightDTO.setIataCode("M01");
        flightDTO.setFlightNumber("M011111");
        flightDTO.setRegular(true);
        flightDTO.setTicketPrice(50.0);
        FlightDTO flightDTO2 = new FlightDTO();
        flightDTO2.setDeparture(LocalDateTime.now());
        flightDTO2.setFlightTime(LocalTime.of(6,0,0,0));
        flightDTO2.setIcaoCodeArrival("1111");
        flightDTO2.setIcaoCodeDeparture("1113");
        flightDTO2.setIataCode("M02");
        flightDTO2.setFlightNumber("M021111");
        flightDTO2.setRegular(false);
        flightDTO2.setTicketPrice(500.0);
        Airplane airplane = new Airplane();
        airplane.setModel("Airbus A380");
        airplane.setIataCode("M01");
        airplane.setLoadCapacity(525);
        Airplane airplane2 = new Airplane();
        airplane2.setModel("Airbus A220");
        airplane2.setIataCode("M02");
        airplane2.setLoadCapacity(160);
        Airplane airplane3 = new Airplane();
        airplane3.setModel("Airbus A319");
        airplane3.setIataCode("M03");
        airplane3.setLoadCapacity(116);
        Airplane airplane4 = new Airplane();
        airplane4.setModel("Airbus A319");
        airplane4.setIataCode("M04");
        airplane4.setLoadCapacity(116);
        Airplane airplane5 = new Airplane();
        airplane5.setModel("Airbus A380");
        airplane5.setIataCode("M05");
        airplane5.setLoadCapacity(407);
        Airport airport = new Airport();
        airport.setCity("Minsk");
        airport.setCountry("Belarus");
        airport.setIcaoCode("1111");
        Airport airport3 = new Airport();
        airport3.setCity("Honkong");
        airport3.setCountry("China");
        airport3.setIcaoCode("1113");
        Airport airport2 = new Airport();
        airport2.setCity("Tarkov");
        airport2.setCountry("Russia");
        airport2.setIcaoCode("1112");
        Airport airport4 = new Airport();
        airport4.setCity("Moscow");
        airport4.setCountry("Russia");
        airport4.setIcaoCode("1114");
        Airport airport5 = new Airport();
        airport5.setCity("Tambov");
        airport5.setCountry("Russia");
        airport5.setIcaoCode("1115");
        Airport airport6 = new Airport();
        airport6.setCity("Ekaterinburg");
        airport6.setCountry("Russia");
        airport6.setIcaoCode("1116");
        airplaneRepository.save(airplane);
        airplaneRepository.save(airplane2);
        airplaneRepository.save(airplane3);
        airplaneRepository.save(airplane4);
        airplaneRepository.save(airplane5);
        airportRepository.save(airport);
        airportRepository.save(airport2);
        airportRepository.save(airport3);
        airportRepository.save(airport4);
        airportRepository.save(airport5);
        airportRepository.save(airport6);
        flightService.create(flightDTO);
        flightService.create(flightDTO2);
    }

    public static void main(String[] args) {
        SpringApplication.run(FlightOrderApplication.class, args);
    }


}
