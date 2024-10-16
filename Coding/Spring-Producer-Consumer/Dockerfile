# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
#WORKDIR /app

# Copy the JAR file into the container
COPY target/demo-0.0.1-SNAPSHOT.jar /producer-consumer.jar

# Expose the port your application will run on
# EXPOSE 8088

# Run the JAR file
ENTRYPOINT ["java", "-jar", "/producer-consumer.jar"]
