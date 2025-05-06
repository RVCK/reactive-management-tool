# Java 21
FROM openjdk:21-jdk-slim

# Set Docker working directory
WORKDIR /app

# Copia el archivo JAR generado por el build de Maven
COPY /target/app-management-tool-*.jar /app/app-management-tool.jar

# Port exposed
EXPOSE 8080

# Run application app-management-tool
ENTRYPOINT ["java", "-jar", "app-management-tool.jar"]
