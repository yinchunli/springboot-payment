FROM alpine/git as clone
WORKDIR /app
RUN git clone https://github.com/yinchunli/springboot-payment.git

FROM maven:3.5-jdk-8-alpine as build
WORKDIR /app
COPY --from=clone /app/springboot-payment /app
RUN mvn install

FROM openjdk:8-jre-alpine
WORKDIR /app
COPY --from=build /app/target/tsys-0.0.1-SNAPSHOT.jar /app
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=local","tsys-0.0.1-SNAPSHOT.jar"]
