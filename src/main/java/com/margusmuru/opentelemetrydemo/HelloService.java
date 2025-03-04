package com.margusmuru.opentelemetrydemo;

import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HelloService {
    private final Tracer tracer;

    public String sayHello() {
        Span span = tracer.spanBuilder("sayHello").startSpan();
        try (Scope scope = span.makeCurrent()) {
            sleep();
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
