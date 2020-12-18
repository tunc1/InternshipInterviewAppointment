FROM openjdk:15.0.1
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","app.jar"]