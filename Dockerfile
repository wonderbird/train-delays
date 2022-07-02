FROM gradle:jdk17 AS builder

WORKDIR /home/gradle/src
COPY --chown=gradle:gradle settings.gradle build.gradle ./
COPY src ./src/
RUN gradle build --no-daemon --exclude-task test

# Please leave out the "-alpine" suffix if you are running on a mac with Apple Silicon chip
FROM eclipse-temurin:17-jre-alpine

COPY --from=builder /home/gradle/src/build/libs/train-delays-0.0.1-SNAPSHOT.jar /train-delays.jar

CMD ["java", "-jar", "/train-delays.jar"]
