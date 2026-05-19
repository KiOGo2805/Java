package org.example.controller;

import org.example.service.InverterService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class TestRunner implements CommandLineRunner {

    private final InverterService inverterService;

    public TestRunner(InverterService inverterService) {
        this.inverterService = inverterService;
    }

    @Override
    public void run(String... args) throws Exception {
        inverterService.testNPlusOne();
    }
}