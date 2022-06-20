FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} eureka-discovery-service.jar
EXPOSE 8761
ENTRYPOINT ["java","-jar","eureka-discovery-service.jar"]