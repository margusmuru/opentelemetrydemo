package com.margusmuru.opentelemetrydemo;

import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String sayHello() {
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "Hello World";
    }

}
