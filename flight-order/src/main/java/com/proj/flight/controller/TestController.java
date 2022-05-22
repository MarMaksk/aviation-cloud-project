package com.proj.flight.controller;

import com.proj.flight.feign.TestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RefreshScope
public class TestController {

    @Value("${test}")
    private String test;

    @Autowired
    public TestController(TestClient departmentClient) {
        this.departmentClient = departmentClient;
    }

    private final TestClient departmentClient;

    @GetMapping()
    public String test() {
        return departmentClient.testKafka(1L);
    }

    @GetMapping("/second")
    public String second() {
        return "True; + " + test;
    }

}
