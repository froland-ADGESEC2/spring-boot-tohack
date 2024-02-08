# syntax=docker/dockerfile:experimental
FROM eclipse-temurin:21-jdk-alpine as builder
WORKDIR /workspace/app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

VOLUME /tmp
ENTRYPOINT ["./mvnw", "spring-boot:run"]
