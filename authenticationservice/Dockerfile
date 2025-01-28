# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file into the container
COPY target/authentication-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port (default for Spring Boot is 8080)
EXPOSE 8081

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
