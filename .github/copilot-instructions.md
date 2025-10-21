# OpenTelemetry Demo Application - Copilot Instructions

## Project Overview

This is a Spring Boot 3.4.2 application designed to demonstrate OpenTelemetry observability patterns. The app generates synthetic data and traces to test observability infrastructure limits (Tempo, Loki, etc.).

## Architecture Patterns

### Core Components
- **Controllers** (`controller/`): REST endpoints for data generation (`/data`, `/db`)
- **Services** (`service/`): Business logic for generating lorem ipsum text and database operations
- **Persistence** (`persistence/`): JPA entities and repositories for PostgreSQL integration
- **Scheduled Tasks** (`scheduledtask/`): Background thread demonstrations

### Key Design Decisions
- **Intentionally inefficient database operations**: `DataGeneratorService.writeDb()` saves rows one-by-one to generate many spans for testing
- **Logging-heavy data generation**: Each lorem ipsum paragraph is logged separately to test log collectors
- **Synthetic workload focus**: All endpoints are designed for load testing, not production patterns

## OpenTelemetry Integration

### Agent-Based Instrumentation
- Uses OpenTelemetry Java agent (auto-instrumentation)
- **Agent location**: Downloaded at container build time or use local `/docker/opentelemetry-javaagent*.jar`
- **Configuration**: Via environment variables, not code

### Required Environment Variables
```bash
JAVA_TOOL_OPTIONS="-javaagent:opentelemetry-javaagent.jar"
OTEL_EXPORTER_OTLP_ENDPOINT=http://192.168.40.112:4317
OTEL_EXPORTER_OTLP_PROTOCOL=grpc
OTEL_LOGS_EXPORTER=otlp
OTEL_METRICS_EXPORTER=otlp
OTEL_SERVICE_NAME=demo
OTEL_TRACES_EXPORTER=otlp
```

### Kubernetes-Specific Configuration
- Pod metadata injection for resource attributes (`POD_NAME`, `K8S_NAMESPACE`, `NODE_NAME`)
- Service discovery via `localhost:32000` registry pattern

## Development Workflows

### Local Development
1. **Database setup**: `cd etc/postgresql && docker compose up -d` (PostgreSQL on port 5433)
2. **Build**: `./gradlew build` (Gradle wrapper, Java 17 toolchain)
3. **Run**: Standard Spring Boot startup with agent env vars

### Container Workflows
- **Dockerfile**: Downloads latest OpenTelemetry agent dynamically
- **Local agent**: Use `/docker/opentelemetry-javaagent-*.jar` for offline builds
- **Kubernetes**: `docker/deployment.yaml` with NodePort service on 30300

### Testing Endpoints
- **`/data?count=N`**: Generates N paragraphs of lorem ipsum with extensive logging
- **`/db?count=N`**: Inserts N database rows individually (requires PostgreSQL)

## Code Conventions

### Lombok Usage
- `@Slf4j` for logging in all classes
- `@RequiredArgsConstructor` for dependency injection
- `@Builder` pattern for entities (`SomeDataEntity`)

### Logging Configuration
- **Level**: `DEBUG` for application package, `WARN` for root
- **Pattern**: Includes thread info and MDC context (`%X`)
- **File**: `src/main/resources/logback.xml`

### Database Patterns
- **Entity naming**: `@Entity(name = "some_data")` maps to PostgreSQL table
- **Connection**: Hardcoded localhost:5433 in `application.properties`
- **Schema**: Manual table creation required (see `etc/postgresql/readme.md`)

## Project-Specific Gotchas

1. **Database operations are intentionally inefficient** - don't optimize the individual saves in `writeDb()`
2. **OpenTelemetry is agent-only** - no manual instrumentation code, all via auto-instrumentation
3. **Environment-dependent endpoints** - collector URLs are hardcoded to specific test infrastructure
4. **Synthetic workload design** - all features exist to generate observability data, not business value