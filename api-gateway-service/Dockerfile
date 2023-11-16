#FROM openjdk:17
#COPY target/*.jar app.jar

FROM openjdk:17-ea-3-jdk-slim-buster
COPY ./target/api-gateway-service-0.0.1-SNAPSHOT.jar /api-gateway-service-0.0.1-SNAPSHOT.jar
#COPY --from=MAVEN_BUILD /prototype/api-gateway-service/target/api-gateway-service-0.0.1-SNAPSHOT.jar /api-gateway-service-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar" , "/api-gateway-service-0.0.1-SNAPSHOT.jar"]
