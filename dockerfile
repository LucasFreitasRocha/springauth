FROM maven:3.9.8-amazoncorretto-17-al2023 AS build
COPY . .
RUN mvn clean install

FROM openjdk:17-jdk-alpine
EXPOSE 8080
RUN addgroup -S spring && adduser -S spring -G spring
USER spring:spring
COPY --from=build /target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]