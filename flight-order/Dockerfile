FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} flight-order-0.0.1-SNAPSHOT.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","/application.jar"]