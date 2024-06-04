# Use the Eclipse Temurin JDK 17 base image
FROM eclipse-temurin:17-jdk-jammy

# Maintainer label
LABEL maintainer="test_user@gmail.com"

# Expose the application port
EXPOSE 8081

# Copy the jar file into the container
COPY target/JobPosting-0.0.1-SNAPSHOT.jar /JobPosting-0.0.1-SNAPSHOT.jar

# Set the entry point to run the jar file
ENTRYPOINT ["java", "-jar", "/JobPosting-0.0.1-SNAPSHOT.jar"]
