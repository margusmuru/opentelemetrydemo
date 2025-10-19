# opentelemetrydemo
attached agent

## Environment variables
```
JAVA_TOOL_OPTIONS="-javaagent:docker/opentelemetry-javaagent-2.14.jar";
OTEL_EXPORTER_OTLP_ENDPOINT=http://192.168.40.112:4317;
OTEL_EXPORTER_OTLP_PROTOCOL=grpc;
OTEL_LOGS_EXPORTER=otlp;
OTEL_METRICS_EXPORTER=otlp;
OTEL_SERVICE_NAME=debugging-hello;
OTEL_TRACES_EXPORTER=otlp
``` 