FROM openjdk:11
EXPOSE 8001
WORKDIR /app
RUN mkdir ./logs
COPY ./target/users-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "users-0.0.1-SNAPSHOT.jar"]
