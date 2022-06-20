FROM adoptopenjdk:11-jre-hotspot
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} flight-order.jar
EXPOSE 9001
ENTRYPOINT ["java","-jar","flight-order.jar"]