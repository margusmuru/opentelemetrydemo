FROM openjdk:17-jdk-slim
EXPOSE 8080
ARG JAR_FILE=build/libs/opentelemetrydemo*.jar
ADD ${JAR_FILE} app.jar
ADD /docker/opentelemetry-javaagent.jar opentelemetry-javaagent.jar
RUN export JAVA_TOOL_OPTIONS="-javaagent:opentelemetry-javaagent.jar"
RUN export OTEL_LOGS_EXPORTER=logging
RUN export OTEL_TRACES_EXPORTER=logging
RUN export OTEL_METRICS_EXPORTER=logging
RUN export OTEL_EXPORTER_OTLP_ENDPOINT=http://opentelemetry-collector-service:30306
RUN export OTEL_SERVICE_NAME="my-hello-world-app"
ENTRYPOINT ["java","-jar","/app.jar"]