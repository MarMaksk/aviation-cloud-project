FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} config-service.jar
EXPOSE 8888
ENTRYPOINT ["java","-jar","config-service.jar"]