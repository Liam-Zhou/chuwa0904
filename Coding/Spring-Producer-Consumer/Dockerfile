# Use an official OpenJDK as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file of your application into the container
COPY target/producer-consumer-app.jar app.jar

# Expose the port (adjust according to your Spring Boot configuration)
EXPOSE 8080

# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
