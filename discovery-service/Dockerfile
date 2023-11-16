
FROM openjdk:17-ea-3-jdk-slim-buster
COPY ./target/discovery-service-0.0.1-SNAPSHOT.jar /discovery-service-0.0.1-SNAPSHOT.jar
#COPY --from=MAVEN_BUILD /prototype/discovery-service/target/discovery-service-0.0.1-SNAPSHOT.jar /discovery-service-0.0.1-SNAPSHOT.jar
CMD ["java", "-jar" , "/discovery-service-0.0.1-SNAPSHOT.jar"]