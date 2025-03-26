# Koristi oficijalnu Java sliku
FROM maven:3.8.5-openjdk-17 AS build
COPY . .
RUN mvn clean package -DskipTests
FROM openjdk:17.0.1-jdk-slim
COPY --from=build target/ConnectFoud-0.0.1-SNAPSHOT.jar ConnectFoud.jar
EXPOSE 8080
ENTRYPOINT [ "java","-jar","ConnectFoud.jar" ]
