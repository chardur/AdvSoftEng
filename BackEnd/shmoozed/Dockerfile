#FROM java:8-jdk-alpine
#FROM openjdk:8-jdk-alpine
FROM openjdk:8-jre-alpine

VOLUME /tmp

COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]