# Use the official maven/Java 17 image to create a build artifact.
FROM maven:3.8.4-openjdk-17 as builder
# Set the working directory in the image to "/app".
WORKDIR /app
# Copy the pom.xml file to our app directory.
COPY pom.xml .
# Build all dependencies for offline use.
RUN mvn dependency:go-offline -B
# Copy the rest of the working directory contents into the image.
COPY src ./src
# Build the application.
RUN mvn package -DskipTests
# Use Oracle's Java 17 image for base image.
FROM openjdk:17-oracle
# Set the working directory in the container
WORKDIR /app
# Copy the jar to the production image from the builder stage.
COPY --from=builder /app/target/*.jar /softball-game-tracker.jar
# Run the web service on container startup.
CMD ["java", "-jar", "/softball-game-tracker.jar"]