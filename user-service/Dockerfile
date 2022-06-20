FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} user-service-0.0.1-SNAPSHOT.jar
EXPOSE 9002
ENTRYPOINT ["java","-jar","/application.jar"]