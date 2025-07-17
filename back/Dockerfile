FROM maven:3.9.8-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src

RUN mvn clean package -DskipTests


FROM openjdk:21-jdk-slim
WORKDIR /app

COPY --from=build /app/target/sge-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8080

ENV DEBIAN_FRONTEND=noninteractive
RUN apt-get update
RUN apt-get install -y curl iputils-ping --no-install-recommends
RUN rm -rf /var/lib/apt/lists/*

ENTRYPOINT ["java", "-jar", "app.jar"]