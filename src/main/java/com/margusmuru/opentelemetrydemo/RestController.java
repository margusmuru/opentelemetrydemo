package com.margusmuru.opentelemetrydemo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RequiredArgsConstructor
@org.springframework.web.bind.annotation.RestController
public class RestController {
    private final HelloService helloService;
    private final ByeService byeService;
    private final MeterService meterService;

    @GetMapping("/hello")
    public String hello() {
        log.info("Received call to /hello endpoint");
        return helloService.sayHello();
    }

    @GetMapping("/bye")
    public String bye() {
        log.info("Received call to /bye endpoint");
        return byeService.sayBye();
    }

    @GetMapping("/meter")
    public String meter() {
        log.info("Received call to /meter endpoint");
        return meterService.meter();
    }

}
