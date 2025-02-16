FROM openjdk:17-jre-slim
EXPOSE 8080
ARG JAR_FILE=build/libs/opentelemetrydemo*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]