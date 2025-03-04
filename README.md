# opentelemetrydemo


# Attached agent
### Environment variables
```
JAVA_TOOL_OPTIONS="-javaagent:docker/opentelemetry-javaagent.jar";
OTEL_EXPORTER_OTLP_ENDPOINT=http://192.168.40.109:4317;
OTEL_EXPORTER_OTLP_PROTOCOL=grpc;
OTEL_LOGS_EXPORTER=otlp;
OTEL_METRICS_EXPORTER=otlp;
OTEL_SERVICE_NAME=debugging-hello;
OTEL_TRACES_EXPORTER=otlp
```

# SDK

### Dependencies
Main dependencies:
```kotlin
implementation("io.opentelemetry:opentelemetry-api")
implementation("io.opentelemetry:opentelemetry-sdk")
implementation("io.opentelemetry:opentelemetry-exporter-logging")
implementation("io.opentelemetry:opentelemetry-exporter-otlp")
implementation("io.opentelemetry:opentelemetry-sdk-extension-autoconfigure")
```
Provides zero-configuration setup for OpenTelemetry SDK. Requires ENV variables \
https://opentelemetry.io/docs/languages/java/configuration/#zero-code-sdk-autoconfigure
```kotlin
implementation("io.opentelemetry:opentelemetry-sdk-extension-autoconfigure:1.47.0")
```

### Environment variables
```
OTEL_EXPORTER_OTLP_ENDPOINT=http://192.168.40.109:4317;
OTEL_EXPORTER_OTLP_PROTOCOL=grpc;
OTEL_LOGS_EXPORTER=logging;
OTEL_METRICS_EXPORTER=logging;
OTEL_SERVICE_NAME=debugging-hello;
OTEL_TRACES_EXPORTER=logging
```