# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk-slim AS build

# Set the working directory inside the container
WORKDIR /app

# Copy the Maven project files to the container
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN ./mvnw clean package -DskipTests

# Create a new image for the runtime
FROM openjdk:17-jre-slim

# Set the working directory for the runtime
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port (default is 8080)
EXPOSE 8080

# Specify the command to run the application
CMD ["java", "-jar", "app.jar"]
