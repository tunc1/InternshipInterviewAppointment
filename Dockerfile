FROM openjdk:16.0.1
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]