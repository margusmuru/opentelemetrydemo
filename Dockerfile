FROM openjdk:17-jdk-slim
EXPOSE 8080
ARG JAR_FILE=build/libs/opentelemetrydemo*.jar
ADD ${JAR_FILE} app.jar
ADD /docker/opentelemetry-javaagent.jar opentelemetry-javaagent.jar
ENV JAVA_TOOL_OPTIONS="-javaagent:opentelemetry-javaagent.jar"
ENV OTEL_LOGS_EXPORTER=otlp
ENV OTEL_TRACES_EXPORTER=logging
ENV OTEL_METRICS_EXPORTER=otlp
ENV OTEL_EXPORTER_OTLP_ENDPOINT=http://192.168.40.109:4317
ENV OTEL_EXPORTER_OTLP_PROTOCOL=grpc
ENV OTEL_SERVICE_NAME=my-hello-world-app
ENTRYPOINT ["java","-jar","/app.jar"]