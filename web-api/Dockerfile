FROM openjdk:23

WORKDIR /

COPY src/target/dai-labo-5-http-api-1.0-SNAPSHOT.jar app.jar

EXPOSE 7070

ENTRYPOINT ["java","-jar","/app.jar"]