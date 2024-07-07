# Use an official Debian image as the base image
FROM debian:bullseye-slim AS build

# Install JDK 19 and Maven
RUN apt-get update && \
    apt-get install -y wget gnupg2 && \
    wget -O- https://apt.corretto.aws/corretto.key | apt-key add - && \
    echo 'deb https://apt.corretto.aws stable main' | tee /etc/apt/sources.list.d/corretto.list && \
    apt-get update && \
    apt-get install -y java-19-amazon-corretto-jdk maven && \
    apt-get clean

# Set the working directory
WORKDIR /home/app

# Copy the project files to the container
COPY . .

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use an official OpenJDK 19 runtime as a parent image
FROM amazoncorretto:19
# Set the working directory
WORKDIR /home/app
# Copy the built JAR file from the build stage
COPY --from=build /home/app/target/*.jar app.jar
# Expose the application port
EXPOSE 8080
# Run the application
ENTRYPOINT ["java", "-jar", "/home/app/app.jar"]
