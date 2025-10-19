package com.margusmuru.opentelemetrydemo.controller;

import com.margusmuru.opentelemetrydemo.service.HelloService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class HelloController {
    private final HelloService helloService;

    @GetMapping("/hello")
    public String hello() {
        log.info("Received call to /hello endpoint");
        return helloService.sayHello();
    }

}
