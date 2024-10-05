# Use an official OpenJDK runtime as the base image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file from the host to the container
COPY target/NASA-APP-STORY-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your application runs on
EXPOSE 8080

# Set the entry point to run your application
ENTRYPOINT ["java", "-jar", "app.jar"]
