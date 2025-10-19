# opentelemetrydemo
attached agent demo

## Environment variables
```
JAVA_TOOL_OPTIONS="-javaagent:docker/opentelemetry-javaagent-2.14.jar";
OTEL_EXPORTER_OTLP_ENDPOINT=http://192.168.40.112:4317;
OTEL_EXPORTER_OTLP_PROTOCOL=grpc;
OTEL_LOGS_EXPORTER=otlp;
OTEL_METRICS_EXPORTER=otlp;
OTEL_SERVICE_NAME=demo;
OTEL_TRACES_EXPORTER=otlp
``` 

## Data endpoints
There are 2 endpoints available to generate a lot of data for demo purposes:
### /data
Add param `count` to define how many paragraphs of lorem ipsum to generate. Each paragraph is logged separately and then finally the whole result is put together, logged, and returned. Added for opentelemetry collector and Loki limits testing
### /db
Note! Requires postgresql database. Refer to `/etc&/postgresql/readme.md` for details.
Add param `count` to define how many rows to add to the demo_db table. Each row is saved and commited separately. It will create a lot of spans. Used to test Tempo limits.
