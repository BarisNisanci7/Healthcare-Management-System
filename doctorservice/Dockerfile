# Base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy jar file into the container
COPY target/doctor-service-0.0.1-SNAPSHOT.jar app.jar

# Expose port
EXPOSE 8083

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.jar"]
