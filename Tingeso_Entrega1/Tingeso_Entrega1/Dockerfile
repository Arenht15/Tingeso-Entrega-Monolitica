FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} Tingeso_Backend_Entrega1.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "./Tingeso_Backend_Entrega1.jar"]