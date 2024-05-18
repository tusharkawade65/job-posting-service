FROM adoptopenjdk/openjdk17
LABEL maintainer="test_user@gmail.com"
EXPOSE 8081
COPY target/JobPosting-1.0.jar JobPosting-1.0.jar
ENTRYPOINT ["java","-jar","/JobPosting-1.0.jar"]