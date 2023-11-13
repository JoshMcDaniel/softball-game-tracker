# syntax = docker/dockerfile:1.2

# Use the official Maven/Java 17 image to create a build artifact.
FROM maven:3.8.5-openjdk-17 as builder

# Set the working directory in the image to "/app".
WORKDIR /app

# Copy the pom.xml file to our app directory.
COPY pom.xml .

# Build all dependencies for offline use.
RUN mvn dependency:go-offline -B

# Copy the rest of the working directory contents into the image.
COPY src ./src

# Make dir for secrets.
RUN mkdir -p /etc/secrets

# Mount secrets.
RUN --mount=type=secret,id=serviceAccountKey_json,dst=/etc/secrets/serviceAccountKey.json cat /etc/secrets/serviceAccountKey.json

# Build the application.
RUN mvn package -DskipTests

# Use OpenJDK 17 image for the base image.
FROM openjdk:17.0.1-jdk-slim

# Set the working directory in the container.
WORKDIR /app

# Copy the JAR file from the builder stage to the production image.
COPY --from=builder /app/target/*.jar /softball-game-tracker.jar

# Run the web service on container startup.
CMD ["java", "-jar", "/softball-game-tracker.jar"]

