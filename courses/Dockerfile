FROM openjdk:11
EXPOSE 8002
WORKDIR /app
RUN mkdir ./logs
COPY ./target/courses-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java", "-jar", "courses-0.0.1-SNAPSHOT.jar"]
