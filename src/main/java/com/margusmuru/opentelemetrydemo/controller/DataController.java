package com.margusmuru.opentelemetrydemo.controller;

import com.margusmuru.opentelemetrydemo.service.DataGeneratorService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class DataController {
    private final DataGeneratorService dataGeneratorService;

    @GetMapping("/data")
    public String data(@RequestParam Long count) {
        log.info("Received call to /data endpoint");
        return dataGeneratorService.generateData(count);
    }

    @GetMapping("/db")
    public String db(@RequestParam Long count) {
        log.info("Received call to /db endpoint");
        return dataGeneratorService.writeDb(count);
    }

}
