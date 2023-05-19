# Use the official OpenJDK image as the base image
FROM openjdk:17-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the compiled Java application and its dependencies into the container
COPY build/libs/*.jar /app/smartgoods.jar

# Expose the port that the application will run on
EXPOSE 8080

# Start the Java application
CMD ["java", "-jar", "/app/smartgoods.jar"]