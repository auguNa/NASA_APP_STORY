# Use OpenJDK 22 as the base image
FROM openjdk:22-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the Maven build output to the working directory
COPY target/nasa-app-0.0.1-SNAPSHOT.jar nasa-app.jar

# Expose the application's port (change this if your application uses a different port)
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "nasa-app.jar"]
