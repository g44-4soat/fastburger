FROM openjdk:17-jdk-slim AS app-challenge
WORKDIR /app
COPY . /app

RUN type=cache, target=/root/.m2 ./mvnw clean install -Dmaven.test.skip=true

EXPOSE 8080
CMD ["java", "-jar", "/app/target/fastburger-0.0.1-SNAPSHOT.jar", "--spring.config.name=docker"]

