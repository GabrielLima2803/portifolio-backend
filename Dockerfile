FROM openjdk:21-jdk-slim

RUN useradd -ms /bin/bash appuser

WORKDIR /app

COPY target/*.jar app.jar

COPY .env .env

RUN chown appuser:appuser app.jar

USER appuser

ENTRYPOINT ["java", "-jar", "app.jar"]
