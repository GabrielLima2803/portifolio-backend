FROM maven:3.9.6-amazoncorretto-21 AS builder
WORKDIR /build
COPY pom.xml .
RUN mvn dependency:go-offline
COPY src ./src
COPY .env ./
RUN mvn clean package -DskipTests

FROM amazoncorretto:21-alpine
RUN addgroup -S appuser && adduser -S appuser -G appuser
USER appuser
WORKDIR /app
COPY --from=builder --chown=appuser:appuser /build/.env ./
COPY --from=builder --chown=appuser:appuser /build/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]