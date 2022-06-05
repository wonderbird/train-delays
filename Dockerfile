FROM eclipse-temurin:17

WORKDIR /root/
COPY ./build/libs/train-delays-0.0.1-SNAPSHOT.jar .
EXPOSE 8080

CMD ["java", "-jar", "train-delays-0.0.1-SNAPSHOT.jar"]
