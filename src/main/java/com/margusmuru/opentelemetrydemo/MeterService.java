package com.margusmuru.opentelemetrydemo;

import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.Meter;
import io.opentelemetry.api.trace.Span;
import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.context.Scope;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MeterService {
    private final Tracer tracer;
    private final Meter meter;
    private final LongCounter requestCounter;

    public MeterService(Tracer tracer, Meter meter) {
        this.tracer = tracer;
        this.meter = meter;
        this.requestCounter = meter.counterBuilder("meter.requests")
                .setDescription("Number of meter requests")
                .build();
    }

    public String meter() {
        requestCounter.add(1);
        Span span = tracer.spanBuilder("meter").startSpan();
        try (Scope scope = span.makeCurrent()) {
            sleep();
            return "Hello meter";
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
