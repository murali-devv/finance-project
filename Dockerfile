FROM openjdk:22-jdk-slim

COPY target/finance-app.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]