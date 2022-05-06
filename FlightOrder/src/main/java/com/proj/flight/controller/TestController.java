package com.proj.flight.controller;

import com.proj.flight.feign.TestClient;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
public class TestController {
    private final TestClient departmentClient;

    @GetMapping()
    public String test() {
        return departmentClient.testKafka(1L);
    }

    @GetMapping("/second")
    public String second() {
        return "True;";
    }

}
