FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/fastburger-0.0.1-SNAPSHOT.jar /app/fastburger-0.0.1-SNAPSHOT.jar
EXPOSE 8080
CMD ["java", "-jar", "/app/fastburger-0.0.1-SNAPSHOT.jar"]

