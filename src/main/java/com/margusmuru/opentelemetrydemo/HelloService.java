package com.margusmuru.opentelemetrydemo;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class HelloService {
    private final Tracer tracer;

    public String sayHello() {
        log.info("Hello World!");
        Span span = tracer.spanBuilder("sayHello").startSpan();
        try (Scope scope = span.makeCurrent()) {
            log.debug("Hello World debug!");
            sleep();
            log.error("Hello World error!");
            return "Hello World";
        } finally {
            span.end();
        }
    }

    private void sleep() {
        Span span = tracer.spanBuilder("sleep").startSpan();
        try (Scope scope = span.makeCurrent()) {
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                span.recordException(e);
                throw new RuntimeException(e);
            }
        } finally {
            span.end();
        }
    }
}
