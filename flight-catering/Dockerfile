FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} flight-catering.jar
EXPOSE 9000
ENTRYPOINT ["java","-jar","flight-catering.jar"]