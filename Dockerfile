# Use an official OpenJDK image
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the generated JAR file to the container
COPY target/nasa-app-0.0.1-SNAPSHOT.jar nasa-app.jar

# Expose the port Spring Boot will run on
EXPOSE 8080

# Run the JAR file
ENTRYPOINT ["java", "-jar", "nasa-app.jar"]
