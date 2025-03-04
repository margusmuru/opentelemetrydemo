package com.margusmuru.opentelemetrydemo;

import io.opentelemetry.api.trace.Tracer;
import io.opentelemetry.sdk.OpenTelemetrySdk;
import io.opentelemetry.sdk.autoconfigure.AutoConfiguredOpenTelemetrySdk;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class OpenTelemetryDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenTelemetryDemoApplication.class, args);
	}

	@Bean
	public static OpenTelemetrySdk autoconfiguredSdk() {
		return AutoConfiguredOpenTelemetrySdk.initialize().getOpenTelemetrySdk();
	}

	@Bean
	public Tracer tracer(OpenTelemetrySdk sdk) {
		return sdk.getTracer("com.margusmuru.opentelemetrydemo");
	}

}
