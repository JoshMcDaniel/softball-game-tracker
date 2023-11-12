# syntax = docker/dockerfile:1.2

# Use the official maven/Java 17 image to create a build artifact.
FROM maven:3.8.5-openjdk-17 as builder

# Set the working directory in the container
WORKDIR /app

RUN --mount=type=secret,id=serviceAccountKey,dst=etc/secrets/serviceAccountKey.json cat etc/secrets/serviceAccountKey.json

# Copy the project files into the container
COPY . .

# Build the application
RUN mvn clean package -DskipTests

# Use OpenJDK 17 image for the base image
FROM openjdk:17.0.1-jdk-slim

# Set the working directory in the container
WORKDIR /app

RUN ls

# Copy the JAR file from the builder stage to the final image
COPY --from=builder /app/target/*.jar /app/softball-game-tracker.jar

# Set the environment variable for the service account key file path
ENV SERVICE_ACCOUNT_KEY=/app/etc/secrets/serviceAccountKey.json

# Expose the port on which the Spring Boot application will run
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "/app/softball-game-tracker.jar"]
