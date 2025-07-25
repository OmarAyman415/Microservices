# Use lightweight JDK base image
FROM eclipse-temurin:23-jdk-temurin

# Set work directory inside container
WORKDIR /app

# Copy JAR file (you’ll build it later)
COPY target/user-service-0.0.1-SNAPSHOT.jar app.jar

# Run app
ENTRYPOINT ["java", "-jar", "app.jar"]
